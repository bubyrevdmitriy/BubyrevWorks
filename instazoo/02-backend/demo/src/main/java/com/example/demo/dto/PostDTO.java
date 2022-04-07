package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String caption;
    private String location;
    private Integer likes;
    private UserNameDTO author;
    private List<ImageDTO> images;


    protected LocalDateTime createdDate;

    private boolean isLikedByLoginUser;
    private boolean isLoginUserAuthor;
}
