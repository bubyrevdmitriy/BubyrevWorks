package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    @NotEmpty
    private String message;
    private UserNameDTO author;

    protected LocalDateTime createdDate;
    private boolean isLoginUserAuthor;
}
