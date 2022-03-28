package com.codeforgeyt.wschatapplication.controller;

import com.codeforgeyt.wschatapplication.storage.ChatStorage;
import com.codeforgeyt.wschatapplication.storage.UserStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin
public class ChatController {
    @GetMapping("/createNewChat/{chatName}")
    public ResponseEntity<Void> createNewChat(@PathVariable String chatName) {
        System.out.println("!!!!!!handling chat create request: " + chatName);
        try {
            ChatStorage.getInstance().setChat(chatName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllChats")
    public Set<String> fetchAll() {
        System.out.println("!!!!!!fetchAllChats()");
        return ChatStorage.getInstance().getChats();
    }
}
