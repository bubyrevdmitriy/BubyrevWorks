package com.example.demo.facade;

import com.example.demo.dto.UserFriendInviteDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFriendInviteFacade {

    private final UserAuthorFacade userAuthorFacade;

    @Autowired
    public UserFriendInviteFacade(UserAuthorFacade userAuthorFacade) {
        this.userAuthorFacade = userAuthorFacade;
    }

    public UserFriendInviteDTO userFriendInviteToUserFriendInviteDTO(UserFriendInvite userFriendInvite, User viewUser) {
        UserFriendInviteDTO userFriendInviteDTO = new UserFriendInviteDTO();

        if (viewUser.equals(userFriendInvite.getRecipientUser())) {
            userFriendInviteDTO.setMyFutureFriend(userAuthorFacade.userToUserAuthorDTO(userFriendInvite.getSenderUser()));
        } else {
            userFriendInviteDTO.setMyFutureFriend(userAuthorFacade.userToUserAuthorDTO(userFriendInvite.getRecipientUser()));
        }

        return userFriendInviteDTO;
    }

}
