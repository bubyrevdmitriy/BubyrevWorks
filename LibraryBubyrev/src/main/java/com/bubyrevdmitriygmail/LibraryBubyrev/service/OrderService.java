package com.bubyrevdmitriygmail.LibraryBubyrev.service;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.*;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookAmountRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.OrderRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookAmountRepo bookAmountRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private OrderTimer orderTimer;

    @Value("${order.time.minutes.wait}")
    private Long orderPeriod;


    public void editOrder(Order order, String orderStatus){
        String oldOrderStatus = String.valueOf(order.getRoles()).substring(1);
        oldOrderStatus = oldOrderStatus.substring(0, oldOrderStatus.length() - 1);
        order.getRoles().clear();
        order.getRoles().add(OrderRole.valueOf(orderStatus));
        System.out.println("old orderRole: " +  oldOrderStatus + " new orderRole: " + orderStatus);

        Book bookFromDb = bookRepo.findBookById(order.getBook().getId());
        BookAmount bookAmountFromDb = bookAmountRepo.findBookAmountByBook(bookFromDb);

        int oldInLibrary, newInLibrary = 0, oldAtUsers, newAtUsers = 0, oldReserved,newReserved=0;
        oldInLibrary = bookAmountFromDb.getBookAmountAtLibrary();
        oldAtUsers = bookAmountFromDb.getBookAmountAtUsers();
        oldReserved = bookAmountFromDb.getBookAmountReserved();

        //SENT,ISSUED, EXPIRED, RETURNED;
        if(oldOrderStatus.equals("SENT")) {
            if(orderStatus.equals("ISSUED")) {
                //Книга была заказана ---> Клиенту книгу выдали
                newInLibrary = oldInLibrary;
                newReserved = oldReserved - 1;
                newAtUsers = oldAtUsers + 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(orderPeriod);
                orderTimer.setOrderId(order.getId());
                orderTimer.timerStart();
            }
            if(orderStatus.equals("EXPIRED")) {
                //Книга была заказана ---> Клиенту книгу выдали --> Клиент просрочил книгу
                newInLibrary = oldInLibrary;
                newReserved = oldReserved - 1;
                newAtUsers = oldAtUsers + 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime((long) 0);
            }
            if(orderStatus.equals("RETURNED")) {
                //Книга была заказана ---> Клиент вернул книгу
                newInLibrary = oldInLibrary + 1;
                newAtUsers = oldAtUsers;
                newReserved = oldReserved - 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
        }

        if(oldOrderStatus.equals("ISSUED")) {
            if(orderStatus.equals("SENT")) {
                //Книга была у клиента ---> Клиент вернул книгу, но оставил её зарезервированной
                newInLibrary = oldInLibrary;
                newAtUsers = oldAtUsers - 1;
                newReserved = oldReserved + 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
            if(orderStatus.equals("EXPIRED")) {
                //Книга была у клиента ---> Книга остается у клиента, но теперь она просрочена
                newInLibrary = oldInLibrary;
                newAtUsers = oldAtUsers;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime((long) 0);
            }
            if(orderStatus.equals("RETURNED")) {
                //Книга была у клиента ---> Клиент вернул книгу(неуспев просрочить)
                newInLibrary = oldInLibrary + 1;
                newAtUsers = oldAtUsers + 1;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
        }

        if(oldOrderStatus.equals("EXPIRED")) {
            if(orderStatus.equals("SENT")) {
                //Книга была у клиента(просрочена) --> Мы получили книгу назад,но оставмили резерв на нее на будущее
                newInLibrary = oldInLibrary;
                newAtUsers = oldAtUsers - 1;
                newReserved = oldReserved + 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
            if(orderStatus.equals("ISSUED")) {
                //Книга была у клиента(просрочена) --> Мы продлили клиенту возможность читать книгу
                newInLibrary = oldInLibrary;
                newAtUsers = oldAtUsers;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(orderPeriod);
                orderTimer.setOrderId(order.getId());
                orderTimer.timerStart();
            }
            if(orderStatus.equals("RETURNED")) {
                //Книга была у клиента(просрочена) --> Клиент вернул книгу
                newInLibrary = oldInLibrary + 1;
                newAtUsers = oldAtUsers-1;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
        }

        if(oldOrderStatus.equals("RETURNED") && oldInLibrary >= 1 ) {
            if(orderStatus.equals("SENT")) {
                //Клиент уже вернул книгу  --> Мы вернули на нее бронь (нужно проверить наличие книги)
                newInLibrary = oldInLibrary -1;
                newAtUsers = oldAtUsers;
                newReserved = oldReserved + 1;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(null);
            }
            if(orderStatus.equals("ISSUED")) {
                //Клиент уже вернул книгу --> Мы выдали ему книгу на руки (нужно проверить наличие книги)
                newInLibrary = oldInLibrary - 1;
                newAtUsers = oldAtUsers + 1;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime(orderPeriod);
                orderTimer.setOrderId(order.getId());
                orderTimer.timerStart();

            }
            if(orderStatus.equals("EXPIRED")) {
                //Клиент уже вернул книгу --> Мы выдали ему книгу на руки и он сразу ее просрочил (нужно проверить наличие книги)
                newInLibrary = oldInLibrary - 1;
                newAtUsers = oldAtUsers + 1;
                newReserved = oldReserved;
                bookAmountFromDb.setAll(newInLibrary, newReserved, newAtUsers);
                order.setStockTime((long) 0);
            }
        }
        bookAmountRepo.save(bookAmountFromDb);
        orderRepo.save(order);
    }

    public void addNewBookToOrder(User user, Book bookFromDb, BookAmount bookAmountFromDb) {
        int oldInLibrary = bookAmountFromDb.getBookAmountAtLibrary();
        int newInLibrary = oldInLibrary - 1;
        int oldReserved = bookAmountFromDb.getBookAmountReserved();
        int newReserved = oldReserved + 1;

        bookAmountFromDb.setBookAmountAtLibrary(newInLibrary);
        bookAmountFromDb.setBookAmountReserved(newReserved);
        bookAmountRepo.save(bookAmountFromDb);

        User userFromDb = userRepo.findByUsername(user.getUsername());
        Order order = new Order(userFromDb, bookFromDb);
        order.setOrderroles(Collections.singleton(OrderRole.SENT));
        orderRepo.save(order);
    }







}
