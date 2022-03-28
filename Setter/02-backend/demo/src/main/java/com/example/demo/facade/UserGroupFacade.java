package com.example.demo.facade;

import com.example.demo.dto.UserGroupDTO;
import com.example.demo.entity.CommonImage;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.service.CommonImageService;
import com.example.demo.service.UserGroupInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserGroupFacade {

    private final CommonImageService commonImageService;
    private final UserGroupInviteService userGroupInviteService;
    private final ImageFacade imageFacade;

    @Autowired
    public UserGroupFacade(CommonImageService commonImageService,
                           UserGroupInviteService userGroupInviteService,
                           ImageFacade imageFacade
    ) {
        this.commonImageService = commonImageService;
        this.userGroupInviteService = userGroupInviteService;
        this.imageFacade = imageFacade;
    }

    public UserGroupDTO userGroupToUserGroupDTO(UserGroup userGroup, User viewUser) {
        UserGroupDTO userGroupDTO = new UserGroupDTO();
        userGroupDTO.setId(userGroup.getId());
        userGroupDTO.setName(userGroup.getName());
        userGroupDTO.setDescription(userGroup.getDescription());
        CommonImage commonImage;

        try {
            commonImage = commonImageService.getProfileImageToUserGroup(userGroup);
            userGroupDTO.setProfilePhoto(imageFacade.imageToImageDTO(commonImage));
        } catch (Exception e) {
            String fileAddress = "C:/Users/bubyr/IdeaProjects/Setter/01-multimedia/images/baseImages/baseBigAvatar.gif";
            commonImage = new CommonImage();
            commonImage.setName("baseBigAvatar.gif");
            commonImage.setAddress(fileAddress);
            try {
                userGroupDTO.setProfilePhoto(imageFacade.imageToImageDTO(commonImage));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        userGroupDTO.setIsLoginUserMember(userGroupInviteService.isUserMemberInUserGroup(viewUser, userGroup));
        userGroupDTO.setIsLoginUserAdmin(userGroupInviteService.isUserAdminInUserGroup(viewUser, userGroup));
        userGroupDTO.setIsLoginUserHasInvite(userGroupInviteService.isUserHasInviteUserGroup(viewUser, userGroup));

        return userGroupDTO;
    }
}
