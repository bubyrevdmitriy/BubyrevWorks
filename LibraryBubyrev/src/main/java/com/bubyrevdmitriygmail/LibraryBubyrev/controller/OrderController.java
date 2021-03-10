package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.BookAmount;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Order;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookAmountRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.OrderRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class OrderController {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookAmountRepo bookAmountRepo;
    @Autowired
    OrderService orderService;

    @GetMapping("/myOrder")
    public String orders(@AuthenticationPrincipal User user, Model model) {

        User userFromDB = userRepo.findUserById(user.getId());

        Set<Order> orders = userFromDB.getOrders();

        model.addAttribute("Orders", orders);
        return "myOrder";
    }

    @PostMapping("/myOrder")
    public String add(@AuthenticationPrincipal User user, @RequestParam("bookId") String bookId, Model model
    ) {

        if ( StringUtils.isEmpty(bookId)) {
            model.addAttribute("bookIdError", "Заполните Id книги!");
        } else {
            Book bookFromDb = bookRepo.findBookById(Long.parseLong(bookId));

            if (bookFromDb == null || StringUtils.isEmpty(bookFromDb)) {
                model.addAttribute("bookIdError", "Такой книги не существует!");
            } else {
                BookAmount bookAmountFromDb = bookAmountRepo.findBookAmountByBook(bookFromDb);

                if (bookAmountFromDb == null || StringUtils.isEmpty(bookAmountFromDb)) {
                    model.addAttribute("bookAmountError", "bookAmountFromDb == null");

                } else {

                    if (bookAmountFromDb.getBookAmountAtLibrary() >= 1) {
                        orderService.addNewBookToOrder(user, bookFromDb, bookAmountFromDb);
                    } else {

                        model.addAttribute("bookAmountError", "Даннные книги в библиотеке закончились!");
                    }
                }
            }
        }
        User userFromDB = userRepo.findUserById(user.getId());

        Set<Order> orders = userFromDB.getOrders();

        model.addAttribute("Orders", orders);
        return "myOrder";
    }
}
