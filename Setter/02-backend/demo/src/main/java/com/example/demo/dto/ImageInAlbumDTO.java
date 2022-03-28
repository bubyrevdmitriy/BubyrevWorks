package com.example.demo.dto;

import lombok.Data;

@Data
public class ImageInAlbumDTO extends ImageDTO {
    private Long nextImageId;
    private Long previousImageId;
    private Integer likes;

    private boolean isLikedByLoginUser;
    private boolean isLoginUserAuthor;
}
