package com.codeforgeyt.wschatapplication.controller;


import com.codeforgeyt.wschatapplication.model.MessageModel;
import com.codeforgeyt.wschatapplication.storage.ChatStorage;
import com.codeforgeyt.wschatapplication.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")//такой же как в Config отсюда приходит сообщение
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists = ChatStorage.getInstance().getChats().contains(to);
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);//такой же как в Config сюда уходят сообщения
        }
    }
}
