package com.example.demo.facade;

import com.example.demo.dto.UserGroupInviteDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroupInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupInviteFacade {

    private final UserAuthorFacade userAuthorFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;

    @Autowired
    public UserGroupInviteFacade(UserAuthorFacade userAuthorFacade, UserGroupAuthorFacade userGroupAuthorFacade) {
        this.userAuthorFacade = userAuthorFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
    }

    public UserGroupInviteDTO userGroupInviteToUserGroupInviteDTO(UserGroupInvite userGroupInvite, User viewUser) {
        UserGroupInviteDTO userGroupInviteDTO = new UserGroupInviteDTO();
        User recipientUser = userGroupInvite.getRecipientUser();
        if (viewUser.getId().equals(recipientUser.getId())) {
            userGroupInviteDTO.setOtherUser(userAuthorFacade.userToUserAuthorDTO(userGroupInvite.getSenderUser()));
        } else {
            userGroupInviteDTO.setOtherUser(userAuthorFacade.userToUserAuthorDTO(userGroupInvite.getRecipientUser()));
        }
        userGroupInviteDTO.setFutureGroup(userGroupAuthorFacade.userGroupToUserGroupAuthorDTO(userGroupInvite.getUserGroup()));
        return userGroupInviteDTO;
    }
}
