package com.example.demo.dto;

import lombok.Data;

@Data
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;

    public ChatNotification(Long id, Long senderId, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }
}
