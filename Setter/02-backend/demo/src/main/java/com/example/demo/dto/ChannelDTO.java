package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChannelDTO {

    private Long id;

    private UserAuthorDTO senderUser;
    private UserAuthorDTO recipientUser;

    private Long senderId;//не используется, кандидат на удаление
    private Long recipientId;//не используется, кандидат на удаление

    private String lastMessage;
    private LocalDateTime lastDate;

}
