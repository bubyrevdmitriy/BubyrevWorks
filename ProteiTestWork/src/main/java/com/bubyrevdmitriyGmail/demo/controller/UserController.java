package com.bubyrevdmitriyGmail.demo.controller;


import com.bubyrevdmitriyGmail.demo.dto.AuthenticationRequestDto;
import com.bubyrevdmitriyGmail.demo.dto.RegistrationRequestDto;
import com.bubyrevdmitriyGmail.demo.model.User;
import com.bubyrevdmitriyGmail.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<Map<Object, Object>> serviceInfo() {

        Map<Object, Object> response = new HashMap<>();
        response.put("projectName", "ProteiTestWork");
        response.put("description","back end part Web service");
        response.put("technologies", "Java, Spring, Spring Security, Hibernate, Rest API, PostgreSQL");
        response.put("author", "Bubyrev Dmitriy");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/registration")
    public ResponseEntity<Map<Object, Object>> registrationInfo() {

        Map<Object, Object> response = new HashMap<>();
        response.put("url", "/registration");
        response.put("description","user registration page");
        response.put("methods", "GET, POST");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<Object, Object>> registrationAddUser(@Valid @RequestBody RegistrationRequestDto requestDto) {

        User userTemp = userService.register(new User(requestDto));

        Map<Object, Object> response = new HashMap<>();
        response.put("userId", userTemp.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public ResponseEntity<Map<Object, Object>> loginInfo() {

        Map<Object, Object> response = new HashMap<>();
        response.put("url", "/login");
        response.put("description","user login page");
        response.put("methods", "GET, POST");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> loginEnter(@RequestBody AuthenticationRequestDto requestDto) {

            String username = requestDto.getUsername();
            String password = requestDto.getPassword();

            User user = userService.loginUser(username, password);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("userId", user.getId());

            return ResponseEntity.ok(response);
    }
}
