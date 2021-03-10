package com.bubyrevdmitriygmail.LibraryBubyrev.service;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Order;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.OrderRole;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.OrderRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class OrderTimer{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private MailSender mailSender;

    @Value("${order.time.minutes.wait}")
    private Long orderPeriod;

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderTimer(Long orderId) {
        this.orderId = orderId;
    }

    public OrderTimer() {
    }

    @Async
    public void timerStart() {
        Order orderFromDb = orderRepo.findOrderById(this.orderId);
        User userFromDb = userRepo.findUserById(orderFromDb.getUser().getId());
        Book bookFromDb = bookRepo.findBookById(orderFromDb.getBook().getId());

        Long stockTime = orderPeriod;
        long milliseconds = stockTime * 60 *1000;
        int timePeriod = 60*1000;//минута
        System.out.println("осталось "+(milliseconds/(60*1000))+" минут");

        while (milliseconds>0){
            try {
                Thread.sleep(timePeriod);
                milliseconds = milliseconds-timePeriod;

                System.out.println("осталось "+(milliseconds/(60*1000))+" минут");
                String oldOrderStatus = String.valueOf(orderFromDb.getRoles()).substring(1);
                oldOrderStatus = oldOrderStatus.substring(0, oldOrderStatus.length() - 1);
                if (oldOrderStatus.equals("ISSUED")) {
                    orderFromDb.setStockTime(milliseconds / (60 * 1000));
                    orderRepo.save(orderFromDb);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String oldOrderStatus = String.valueOf(orderFromDb.getRoles()).substring(1);
        oldOrderStatus = oldOrderStatus.substring(0, oldOrderStatus.length() - 1);
        if (oldOrderStatus.equals("ISSUED")) {
            orderFromDb.getRoles().clear();
            orderFromDb.getRoles().add(OrderRole.valueOf("EXPIRED"));
            orderRepo.save(orderFromDb);

            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Срок чтение книги %s по вашему заказу %s истек! Пожалуйста верните книгу в библиотеку!",
                    userFromDb.getUsername(),
                    bookFromDb.getName(),
                    orderFromDb.getId());

            mailSender.send( userFromDb.getEmail(), "Возврат книги", message);
        }
    }
}
