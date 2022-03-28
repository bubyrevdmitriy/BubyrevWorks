package com.example.demo.facade;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.ImageInAlbumDTO;
import com.example.demo.entity.BaseEntity.BaseFile;
import com.example.demo.entity.CommonImage;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.service.CommonImageService;
import com.example.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class ImageFacade {
    private final CommonImageService commonImageService;
    private final UserLikeService userLikeService;

    @Autowired
    public ImageFacade(CommonImageService commonImageService,
                       UserLikeService userLikeService
    ) {
        this.commonImageService = commonImageService;
        this.userLikeService = userLikeService;
    }

    public ImageDTO imageToImageDTO(BaseFile baseFile) throws IOException {
        ImageDTO imageDTO = new ImageDTO();
        String address = baseFile.getAddress();
        imageDTO.setId(baseFile.getId());
        imageDTO.setName(baseFile.getName());
        imageDTO.setImageBytes(convertImageAddressToByteArray(address));
        imageDTO.setCreatedDate(baseFile.getCreatedDate());
        return imageDTO;
    }

    public ImageInAlbumDTO imageToImageInAlbumDTO(CommonImage commonImage, User loginUser) throws IOException {
        ImageInAlbumDTO imageInAlbumDTO = new ImageInAlbumDTO();
        String address = commonImage.getAddress();
        imageInAlbumDTO.setId(commonImage.getId());
        imageInAlbumDTO.setLikes(commonImage.getLikes());
        imageInAlbumDTO.setName(commonImage.getName());
        imageInAlbumDTO.setImageBytes(convertImageAddressToByteArray(address));
        imageInAlbumDTO.setCreatedDate(commonImage.getCreatedDate());
        UserGroup userGroup;
        User user;
        Long previousImageId = null, nextImageId = null;
        List<CommonImage> commonImages;
        userGroup = commonImage.getUserGroup();
        user = commonImage.getUser();
        if (userGroup != null) {
            commonImages = commonImageService.getImagesToUserGroup(userGroup);
        } else {
            commonImages = commonImageService.getImagesToUser(user.getId());
        }
        if (commonImages.size()>1) {
            for (int i = 0; i < commonImages.size(); i++) {
                if (commonImages.get(i).equals(commonImage)) {
                    if (commonImages.get(i).equals(commonImages.get(0))) {
                        previousImageId = commonImages.get(commonImages.size()-1).getId();
                        nextImageId = commonImages.get(i+1).getId();
                    } else if (commonImages.get(i).equals(commonImages.get(commonImages.size()-1))) {
                        previousImageId = commonImages.get(i-1).getId();
                        nextImageId = commonImages.get(0).getId();
                    } else {
                        previousImageId = commonImages.get(i-1).getId();
                        nextImageId = commonImages.get(i+1).getId();
                    }
                    imageInAlbumDTO.setPreviousImageId(previousImageId);
                    imageInAlbumDTO.setNextImageId(nextImageId);
                }
            }
        }
        imageInAlbumDTO.setLikedByLoginUser(userLikeService.isUserLikeCommonImage(loginUser, commonImage));
        imageInAlbumDTO.setLoginUserAuthor(commonImageService.isUserAuthor(commonImage, loginUser));
        return imageInAlbumDTO;
    }

    public byte[] convertImageAddressToByteArray(String address) throws IOException {
        File file = new File(address);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
}
