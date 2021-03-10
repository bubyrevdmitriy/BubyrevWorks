package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Order;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Role;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @GetMapping
    public String userList(
            @RequestParam(required = false, defaultValue =  "") String userUsername,
            @PageableDefault( sort={ "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<User> page = userRepo.findAll(pageable);

        if (userUsername != null && !userUsername.isEmpty()){
            page = userRepo.findByUsername(userUsername, pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/user");
        return "userList";
    }


    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @GetMapping("{user}")
    public String userEditForm(
            @PathVariable String user,
            Model model
    ) {

        User userFromDB = userRepo.findUserById(Long.valueOf(user));

        Set<Order> orders = userFromDB.getOrders();
        model.addAttribute("Orders", orders);

        model.addAttribute("userCurrent", userFromDB);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{user}")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @PathVariable String user,
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam("file") MultipartFile file,
            Model model
            ) throws IOException {
        User userFromDB = userRepo.findUserById(Long.valueOf(user));
        userService.saveUser(userFromDB, username, form);
        userService.updateProfile(userFromDB,  firstName, middleName, lastName, email, phone, password, file);

        Set<Order> orders = userFromDB.getOrders();
        model.addAttribute("Orders", orders);

        model.addAttribute("userCurrent", userFromDB);
        model.addAttribute("roles", Role.values());
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        User userFromDB = userRepo.findUserById(user.getId());

        Set<Order> orders = userFromDB.getOrders();
        model.addAttribute("Orders", orders);

        model.addAttribute("userCurrent", userFromDB);
        return "profile";
    }


    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam("file") MultipartFile file, Model model
    ) throws IOException {
        userService.updateProfile(user,  firstName, middleName, lastName, email, phone, password, file);


        User userFromDB = userRepo.findUserById(user.getId());

        Set<Order> orders = userFromDB.getOrders();
        model.addAttribute("Orders", orders);

        model.addAttribute("userCurrent", userFromDB);
        return "profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{userId}")
    public String userDeleteForm(@PathVariable String userId, Model model) {

        User userFromDB = userRepo.findUserById(Long.valueOf(userId));

        if (!userFromDB.isAdmin()) {
            userRepo.delete(userFromDB);
        }
        model.addAttribute("users", userService.findAll());
        return "redirect:/user";
    }
}
