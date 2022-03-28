package com.example.demo.facade;

import com.example.demo.dto.MessageDTO;
import com.example.demo.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFacade {

    public MessageDTO messageToMessageDTO (Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setRecipientId(message.getRecipient().getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setStatus(message.getStatus());
        messageDTO.setCreatedDate(message.getCreatedDate());
        return messageDTO;
    }
}
