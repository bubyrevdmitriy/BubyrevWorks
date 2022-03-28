package com.example.demo.facade;

import com.example.demo.dto.UserFriendPairDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFriendPairFacade {

    private final UserAuthorFacade userAuthorFacade;

    @Autowired
    public UserFriendPairFacade(UserAuthorFacade userAuthorFacade) {
        this.userAuthorFacade = userAuthorFacade;
    }

    public UserFriendPairDTO userFriendPairToUserFriendPairDTO(UserFriendPair userFriendPair, User viewUser){
        UserFriendPairDTO userFriendPairDTO = new UserFriendPairDTO();

        if (viewUser.equals(userFriendPair.getRecipientUser())) {
            userFriendPairDTO.setMyFriend(userAuthorFacade.userToUserAuthorDTO(userFriendPair.getSenderUser()));
        } else {
            userFriendPairDTO.setMyFriend(userAuthorFacade.userToUserAuthorDTO(userFriendPair.getRecipientUser()));
        }

        return userFriendPairDTO;
    }

}
