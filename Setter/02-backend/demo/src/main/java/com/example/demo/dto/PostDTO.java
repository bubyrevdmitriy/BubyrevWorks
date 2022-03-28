package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDTO {

    private Long id;
    private String caption;
    private Integer likes;
    //private List<UserAuthorDTO> userLiked;
    private UserAuthorDTO userAuthor;
    private UserGroupAuthorDTO userGroupAuthor;
    private List<ImageDTO> commonImages;
    private List<CommentDTO> comments;
    protected LocalDateTime createdDate;

    private boolean isLikedByLoginUser;
    private boolean isLoginUserAuthor;

    private Long nextPostId;
    private Long previousPostId;
}
