package com.example.demo.facade;

import com.example.demo.dto.UserGroupAuthorDTO;
import com.example.demo.entity.SmallAvatarGroup;
import com.example.demo.entity.UserGroup;
import com.example.demo.service.SmallAvatarGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserGroupAuthorFacade {

    private final SmallAvatarGroupService smallAvatarGroupService;
    private final ImageFacade imageFacade;

    @Autowired
    public UserGroupAuthorFacade(SmallAvatarGroupService smallAvatarGroupService, ImageFacade imageFacade) {
        this.smallAvatarGroupService = smallAvatarGroupService;
        this.imageFacade = imageFacade;
    }

    public UserGroupAuthorDTO userGroupToUserGroupAuthorDTO(UserGroup userGroup) {

        UserGroupAuthorDTO userGroupAuthorDTO = new UserGroupAuthorDTO();
        userGroupAuthorDTO.setId(userGroup.getId());
        userGroupAuthorDTO.setName(userGroup.getName());
        SmallAvatarGroup smallAvatarGroup;
        try {
            smallAvatarGroup = smallAvatarGroupService.getSmallAvatarGroupById(userGroup.getId());
            try {
                userGroupAuthorDTO.setSmallAvatar(imageFacade.imageToImageDTO(smallAvatarGroup));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                String fileAddress = "C:/Users/bubyr/IdeaProjects/Setter/01-multimedia/images/baseImages/baseSmallAvatar.gif";
                smallAvatarGroup = new SmallAvatarGroup();
                smallAvatarGroup.setName("baseSmallAvatar.gif");
                smallAvatarGroup.setAddress(fileAddress);
                userGroupAuthorDTO.setSmallAvatar(imageFacade.imageToImageDTO(smallAvatarGroup));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return userGroupAuthorDTO;
    }


}
