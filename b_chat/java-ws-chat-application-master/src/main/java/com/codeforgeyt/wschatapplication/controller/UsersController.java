package com.codeforgeyt.wschatapplication.controller;


import com.codeforgeyt.wschatapplication.storage.UserStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
public class UsersController {

    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("!!!!!!handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAllUsers() {
        System.out.println("!!!!!!fetchAllUsers()");
        return UserStorage.getInstance().getUsers();
    }

    @GetMapping("/")
    public String showBaseInfo() {
        return "Trying to make chat";
    }


}
