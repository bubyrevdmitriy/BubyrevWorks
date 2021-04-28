package com.bubyrevdmitriyGmail.demo.controller;


import com.bubyrevdmitriyGmail.demo.dto.ChangeUserStatusDto;
import com.bubyrevdmitriyGmail.demo.model.User;
import com.bubyrevdmitriyGmail.demo.model.UserStatus;
import com.bubyrevdmitriyGmail.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserListController {
    private final UserService userService;

    @Autowired
    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<Object, Object>> allUsersToPage() {

        List allUser = userService.getAll();
        Map<Object, Object> response = new HashMap<>();

        for (int i = 0; i < allUser.size(); i++) {
            User user = (User) allUser.get(i);
            Map<Object, Object> userMap = new HashMap<>();
            putOneUserMap(userMap, user);
            response.put("user" + " " + (i + 1), userMap);
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<Map<Object, Object>> updateUserStatus(@RequestBody ChangeUserStatusDto requestDto){

            Long id = requestDto.getId();
            User user = userService.findUserById(id);

            UserStatus oldUserStatus = user.getUserStatus();

            User userAfterChange = userService.updateUserStatus(id, requestDto.getNewStatus());

            Map<Object, Object> response = new HashMap<>();
            response.put("userId", user.getId());
            response.put("oldStatus", oldUserStatus.name());
            response.put("newStatus", userAfterChange.getUserStatus().name());

            return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Map<Object, Object>> userPage(@PathVariable("id") Long id) {

        User userFromDb = userService.findUserById(id);
        Map<Object, Object> response = new HashMap<>();
        putOneUserMap(response, userFromDb);

        return ResponseEntity.ok(response);
    }

    public Map<Object, Object> putOneUserMap(Map response, User user) {
        response.put("userId", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("phoneNumber", user.getPhoneNumber());
        response.put("userRoles", user.getUserRoles());
        response.put("UserStatus", user.getUserStatus());

        return response;
    }


}
