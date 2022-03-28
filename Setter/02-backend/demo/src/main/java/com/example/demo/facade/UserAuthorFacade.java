package com.example.demo.facade;

import com.example.demo.dto.UserAuthorDTO;
import com.example.demo.entity.SmallAvatar;
import com.example.demo.entity.User;
import com.example.demo.service.SmallAvatarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthorFacade {

    private final SmallAvatarUserService smallAvatarUserService;
    private final ImageFacade imageFacade;

    @Autowired
    public UserAuthorFacade(SmallAvatarUserService smallAvatarUserService,
                            ImageFacade imageFacade
    ) {
        this.smallAvatarUserService = smallAvatarUserService;
        this.imageFacade = imageFacade;
    }

    public UserAuthorDTO userToUserAuthorDTO(User user) {

        UserAuthorDTO userAuthorDTO = new UserAuthorDTO();
        userAuthorDTO.setId(user.getId());
        userAuthorDTO.setFirstName(user.getFirstName());
        userAuthorDTO.setLastName(user.getLastName());
        userAuthorDTO.setMiddleName(user.getMiddleName());
        SmallAvatar smallAvatar;

        try {
            smallAvatar = smallAvatarUserService.getSmallAvatarById(user.getId());
            try {
                userAuthorDTO.setSmallAvatar(imageFacade.imageToImageDTO(smallAvatar));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                String fileAddress = "C:/Users/bubyr/IdeaProjects/Setter/01-multimedia/images/baseImages/baseSmallAvatar.gif";
                smallAvatar = new SmallAvatar();
                smallAvatar.setName("baseSmallAvatar.gif");
                smallAvatar.setAddress(fileAddress);
                userAuthorDTO.setSmallAvatar(imageFacade.imageToImageDTO(smallAvatar));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return userAuthorDTO;
    }

}
