package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Book;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Order;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.OrderRole;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.BookAmountRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.OrderRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/orderList")
@PreAuthorize("hasAuthority('LIBRARIAN')")
public class OrderListController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    BookAmountRepo bookAmountRepo;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderList(
            @RequestParam(required = false, defaultValue =  "") String orderId,
            @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<Order> page = orderRepo.findAll(pageable);

        if (orderId != null && !orderId.isEmpty()){
            page =orderRepo.findOrderById(Long.valueOf(orderId), pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/orderList");
        model.addAttribute("roles", OrderRole.values());
        return "orderList";
    }

    @GetMapping("{order}")
    public String orderEditForm(@PathVariable Order order, Model model) {

        User user = order.getUser();
        Book book = order.getBook();

        model.addAttribute("order", order);
        model.addAttribute("userCurrent", user);
        model.addAttribute("book", book);
        model.addAttribute("roles", OrderRole.values());
        return "orderEdit";
    }

    @PostMapping("{order}")
    public String orderSave(
            @RequestParam Map<String, String> form,
            @RequestParam String orderStatus,
            @RequestParam("orderId") Order order
    ) {
        orderService.editOrder(order, orderStatus);

        return "redirect:/orderList";
    }

    @GetMapping("delete/{orderId}")
    public String orderDelete(@PathVariable String orderId, @AuthenticationPrincipal User user, Model model) {

        Order orderFromDb = orderRepo.findOrderById(Long.valueOf(orderId));
        User orderOwner = orderFromDb.getUser();
        boolean isUserOwner = user.getId().equals(orderOwner.getId());
        boolean isUserLibrarian = user.isLibrarian();
        if (isUserOwner || isUserLibrarian) {
            orderRepo.delete(orderFromDb);
        }
        return "redirect:/orderList";
    }
}
