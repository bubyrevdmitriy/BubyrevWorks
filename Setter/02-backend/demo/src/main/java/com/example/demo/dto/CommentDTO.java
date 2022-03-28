package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long id;
    @NotEmpty
    private String text;
    //private List<UserAuthorDTO> userLiked;
    private Integer likes;
    private UserAuthorDTO userAuthor;
    private Long postId;
    private Long commonImageId;
    protected LocalDateTime createdDate;

    private boolean isLikedByLoginUser;
    private boolean isLoginUserAuthor;
}
