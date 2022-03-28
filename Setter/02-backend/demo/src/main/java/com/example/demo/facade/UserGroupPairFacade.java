package com.example.demo.facade;

import com.example.demo.dto.UserGroupPairForUserDTO;
import com.example.demo.dto.UserGroupPairForUserGroupDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroupPair;
import com.example.demo.service.UserGroupInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupPairFacade {

    private final UserAuthorFacade userAuthorFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;
    private final UserGroupInviteService userGroupInviteService;

    @Autowired
    public UserGroupPairFacade(UserAuthorFacade userAuthorFacade,
                               UserGroupAuthorFacade userGroupAuthorFacade,
                               UserGroupInviteService userGroupInviteService
    ) {
        this.userAuthorFacade = userAuthorFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
        this.userGroupInviteService = userGroupInviteService;
    }

    public UserGroupPairForUserDTO userGroupPairToUserGroupPairForUserDTO(UserGroupPair userGroupPair, User viewUser){
        UserGroupPairForUserDTO userGroupPairForUserDTO = new UserGroupPairForUserDTO();
        userGroupPairForUserDTO.setUserId(userGroupPair.getUser().getId());
        userGroupPairForUserDTO.setIsUserAdmin(userGroupInviteService.isUserAdminInUserGroup(userGroupPair.getUser(), userGroupPair.getUserGroup()));
        userGroupPairForUserDTO.setMyGroup(userGroupAuthorFacade.userGroupToUserGroupAuthorDTO(userGroupPair.getUserGroup()));
        return userGroupPairForUserDTO;
    }

    public UserGroupPairForUserGroupDTO userGroupPairToUserGroupPairForUserGroupDTO(UserGroupPair userGroupPair, User viewUser){
        UserGroupPairForUserGroupDTO userGroupPairForUserGroupDTO = new UserGroupPairForUserGroupDTO();
        userGroupPairForUserGroupDTO.setUserGroupId(userGroupPair.getUserGroup().getId());
        userGroupPairForUserGroupDTO.setIsUserAdmin(userGroupInviteService.isUserAdminInUserGroup(userGroupPair.getUser(), userGroupPair.getUserGroup()));
        userGroupPairForUserGroupDTO.setUser(userAuthorFacade.userToUserAuthorDTO(userGroupPair.getUser()));
        return userGroupPairForUserGroupDTO;
    }
}
