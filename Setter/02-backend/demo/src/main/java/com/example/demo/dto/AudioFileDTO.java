package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AudioFileDTO {

    private Long id;
    private Integer likes;
    private UserAuthorDTO userAuthor;
    private UserGroupAuthorDTO userGroupAuthor;


    private String description;
    private String contentType;
    private String previewUrl;
    private String streamUrl;


    private List<CommentDTO> comments;
    protected LocalDateTime createdDate;

    private boolean isLikedByLoginUser;
    private boolean isLoginUserAuthor;

    private Long nextAudioFileId;
    private Long previousAudioFileId;
}
