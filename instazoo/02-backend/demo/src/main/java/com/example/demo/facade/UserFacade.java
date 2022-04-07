package com.example.demo.facade;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserFacade {

    private final ImageService imageService;
    private final ImageFacade imageFacade;

    @Autowired
    public UserFacade(ImageService imageService,
                      ImageFacade imageFacade
    ) {
        this.imageService = imageService;
        this.imageFacade = imageFacade;
    }

    public UserDTO userToUserDTO(User user, User loginUser) throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());

        if (user.getId().equals(loginUser.getId())) {
            userDTO.setIsLoginUserPage(true);
        } else {
            userDTO.setIsLoginUserPage(false);
        }

        if (user.getId().equals(loginUser.getId())) {
            userDTO.setIsLoginUserPage(true);
        } else {
            userDTO.setIsLoginUserPage(false);
        }

        try {
            userDTO.setProfileImage(imageFacade.imageToImageDTO(user.getProfileImage()));
        } catch (Exception e) {
        }

        return userDTO;
    }

}
