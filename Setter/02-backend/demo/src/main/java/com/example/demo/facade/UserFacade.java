package com.example.demo.facade;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.*;
import com.example.demo.service.ChannelService;
import com.example.demo.service.CommonImageService;
import com.example.demo.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserFacade {

    private final CommonImageService commonImageService;
    private final UserFriendService userFriendService;
    private final ChannelService channelService;
    private final ImageFacade imageFacade;

    @Autowired
    public UserFacade(CommonImageService commonImageService,
                      ImageFacade imageFacade,
                      UserFriendService userFriendService,
                      ChannelService channelService
    ) {
        this.commonImageService = commonImageService;
        this.userFriendService = userFriendService;
        this.imageFacade = imageFacade;
        this.channelService = channelService;
    }

    public UserDTO userToUserDTO(User user, User loginUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setBio(user.getBio());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setBornDate(user.getBornDate());
        CommonImage commonImage;

        try {
            commonImage = commonImageService.getProfileImageToUser(user);
            try {
                userDTO.setProfilePhoto(imageFacade.imageToImageDTO(commonImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                String fileAddress = "C:/Users/bubyr/IdeaProjects/Setter/01-multimedia/images/baseImages/baseBigAvatar.gif";
                commonImage = new CommonImage();
                commonImage.setName("baseBigAvatar.gif");
                commonImage.setAddress(fileAddress);
                userDTO.setProfilePhoto(imageFacade.imageToImageDTO(commonImage));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (user.getId().equals(loginUser.getId())) {
            userDTO.setIsLoginUserPage(true);
        } else {
            userDTO.setIsLoginUserPage(false);
            UserFriendPair userFriendPairExist;
            try {
                userFriendPairExist = userFriendService.getFriendPairByUsers(user, loginUser);
            } catch (Exception e) {
                userFriendPairExist = null;
            }
            if(userFriendPairExist != null) {
                userDTO.setIsLoginUserFriend(true);
            } else {
                userDTO.setIsLoginUserFriend(false);
                UserFriendInvite userFriendInviteFromUserToLoginUser;
                UserFriendInvite userFriendInviteFromLoginUserToUser;
                try {
                    userFriendInviteFromUserToLoginUser = userFriendService.getFriendInviteBySenderAndRecipient(user, loginUser);
                } catch (Exception e) {
                    userFriendInviteFromUserToLoginUser = null;
                }
                try {
                    userFriendInviteFromLoginUserToUser = userFriendService.getFriendInviteBySenderAndRecipient(loginUser, user);
                } catch (Exception e) {
                    userFriendInviteFromLoginUserToUser = null;
                }

                userDTO.setSendToLoginUserFriendInvite(userFriendInviteFromUserToLoginUser != null);

                userDTO.setReceiveFromLoginUserFriendInvite(userFriendInviteFromLoginUserToUser != null);
            }
        }
        Channel channel;
        channel = channelService.getChannelByUsers(user, loginUser);
        if(channel != null) {
            userDTO.setChannelIdWithLoginUserPage(channel.getId());
        }
        return userDTO;
    }
}
