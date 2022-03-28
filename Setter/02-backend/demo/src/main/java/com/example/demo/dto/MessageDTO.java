package com.example.demo.dto;

import com.example.demo.entity.enums.MessageStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MessageDTO {

    private Long id;

    private Long senderId;
    private Long recipientId;

    @NotEmpty
    private String content;

    protected LocalDateTime createdDate;

    private String channelId;
    private MessageStatus status;
}
