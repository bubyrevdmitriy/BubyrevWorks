package com.example.demo.controller;

import com.example.demo.dto.UserGroupAuthorDTO;
import com.example.demo.dto.UserGroupInviteDTO;
import com.example.demo.dto.UserGroupPairForUserDTO;
import com.example.demo.dto.UserGroupPairForUserGroupDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroupInvite;
import com.example.demo.entity.UserGroupPair;
import com.example.demo.facade.UserGroupAuthorFacade;
import com.example.demo.facade.UserGroupInviteFacade;
import com.example.demo.facade.UserGroupPairFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.UserGroupInviteService;
import com.example.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/group")
@CrossOrigin
public class GroupInviteController {

    private final UserGroupInviteService userGroupInviteService;
    private final UserGroupInviteFacade userGroupInviteFacade;
    private final UserGroupPairFacade userGroupPairFacade;
    private final UtilsService utilsService;
    private final UserGroupAuthorFacade userGroupAuthorFacade;

    @Autowired
    public GroupInviteController(UserGroupInviteService userGroupInviteService,
                                 UserGroupInviteFacade userGroupInviteFacade,
                                 UserGroupPairFacade userGroupPairFacade,
                                 UtilsService utilsService,
                                 UserGroupAuthorFacade userGroupAuthorFacade
    ) {
        this.userGroupInviteService = userGroupInviteService;
        this.userGroupInviteFacade = userGroupInviteFacade;
        this.userGroupPairFacade = userGroupPairFacade;
        this.utilsService = utilsService;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
    }



    @GetMapping("/groupInvite/user/{userId}/send")
    public ResponseEntity<List<UserGroupInviteDTO>> getUserGroupInviteSendForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserGroupInvite> userGroupInviteSendList = userGroupInviteService.getUserGroupInvitesSendFromUser(Long.valueOf(userId));
        List<UserGroupInviteDTO> userGroupInviteSendDTO = new ArrayList<>();
        for (UserGroupInvite groupInvite : userGroupInviteSendList) {
            userGroupInviteSendDTO.add(userGroupInviteFacade.userGroupInviteToUserGroupInviteDTO(groupInvite, hostUser));
        }
        return new ResponseEntity<>(userGroupInviteSendDTO, HttpStatus.OK);
    }

    @GetMapping("/groupInvite/user/{userId}/received")
    public ResponseEntity<List<UserGroupInviteDTO>> getUserGroupInviteReceivedForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserGroupInvite> userGroupInviteRecievedList = userGroupInviteService.getUserGroupInvitesRecievedFromUser(Long.valueOf(userId));
        List<UserGroupInviteDTO> userGroupInviteRecievedDTOList = new ArrayList<>();
        for (UserGroupInvite groupInvite : userGroupInviteRecievedList) {
            userGroupInviteRecievedDTOList.add(userGroupInviteFacade.userGroupInviteToUserGroupInviteDTO(groupInvite, hostUser));
        }
        return new ResponseEntity<>(userGroupInviteRecievedDTOList, HttpStatus.OK);
    }

    @GetMapping("/groupInvite/user/{userId}/availableToInvite")
    public ResponseEntity<List<UserGroupAuthorDTO>> getUserGroupAvailableToInviteForUser(@PathVariable("userId") String userId, Principal principal) {
        List<UserGroupAuthorDTO> userAuthorDTOList = userGroupInviteService.getUserGroupAvailableToInviteForUser(Long.valueOf(userId), principal)
                .stream()
                .map(userGroupAuthorFacade::userGroupToUserGroupAuthorDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @GetMapping("/groupPair/user/{userId}/all")
    public ResponseEntity<List<UserGroupPairForUserDTO>> getUserPairsForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserGroupPair> userGroupPairList = userGroupInviteService.getUserGroupPairsFromUser(Long.valueOf(userId));
        List<UserGroupPairForUserDTO> userGroupPairDTOList = new ArrayList<>();
        for (UserGroupPair groupPair : userGroupPairList) {
            userGroupPairDTOList.add(userGroupPairFacade.userGroupPairToUserGroupPairForUserDTO(groupPair,hostUser));
        }
        return new ResponseEntity<>( userGroupPairDTOList, HttpStatus.OK);
    }

    @GetMapping("/groupInvite/userGroup/{userGroupId}/all")
    public ResponseEntity<List<UserGroupInviteDTO>> getUserInvitesForUserGroup(@PathVariable("userGroupId") String userGroupId, Principal principal) {
        User hostUser = utilsService.getUserByPrincipal(principal);
        List<UserGroupInvite> userGroupInviteList = userGroupInviteService.getAllUserGroupInvitesFromUserGroup(Long.valueOf(userGroupId));
        List<UserGroupInviteDTO> userGroupInviteDTOList = new ArrayList<>();
        for (UserGroupInvite groupInvite : userGroupInviteList) {
            userGroupInviteDTOList.add(userGroupInviteFacade.userGroupInviteToUserGroupInviteDTO(groupInvite,hostUser));
        }
        return new ResponseEntity<>( userGroupInviteDTOList, HttpStatus.OK);
    }

    @GetMapping("/groupPair/userGroup/{userGroupId}/all")
    public ResponseEntity<List<UserGroupPairForUserGroupDTO>> getUserPairsForUserGroup(@PathVariable("userGroupId") String userGroupId, Principal principal) {
        User hostUser = utilsService.getUserByPrincipal(principal);
        List<UserGroupPair> userGroupPairList = userGroupInviteService.getUserGroupPairsFromUserGroup(Long.valueOf(userGroupId));
        List<UserGroupPairForUserGroupDTO> userGroupPairDTOList = new ArrayList<>();
        for (UserGroupPair groupPair : userGroupPairList) {
            userGroupPairDTOList.add(userGroupPairFacade.userGroupPairToUserGroupPairForUserGroupDTO(groupPair,hostUser));
        }
        return new ResponseEntity<>( userGroupPairDTOList, HttpStatus.OK);
    }

    @GetMapping("/groupPair/userGroup/{userGroupId}/admin")
    public ResponseEntity<List<UserGroupPairForUserGroupDTO>> getUserPairAdminsForUserGroup(@PathVariable("userGroupId") String userGroupId, Principal principal) {
        User hostUser = utilsService.getUserByPrincipal(principal);
        List<UserGroupPair> userGroupPairList = userGroupInviteService.getUserGroupPairAdminsFromUserGroup(Long.valueOf(userGroupId));
        List<UserGroupPairForUserGroupDTO> userGroupPairDTOList = new ArrayList<>();
        for (UserGroupPair groupPair : userGroupPairList) {
            userGroupPairDTOList.add(userGroupPairFacade.userGroupPairToUserGroupPairForUserGroupDTO(groupPair,hostUser));
        }
        return new ResponseEntity<>( userGroupPairDTOList, HttpStatus.OK);
    }

    @PostMapping("/groupInvite/{userGroupId}/create/{userId}")
    public ResponseEntity<MessageResponse> inviteUserToUserGroup (@PathVariable("userGroupId") String userGroupId, @PathVariable("userId") String userId, Principal principal) {
        Boolean result = userGroupInviteService.inviteUserToGroup(Long.valueOf(userGroupId), Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Group invite was created"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Group invite to was not created, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/groupInvite/{userGroupId}/deny")
    public ResponseEntity<MessageResponse> denyUserGroupInvitation (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        Boolean result = userGroupInviteService.denyUserGroupInvitation(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Group invite was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Group invite was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/groupInvite/{userGroupId}/deny/{userId}")
    public ResponseEntity<MessageResponse> denyUserGroupInvitation (@PathVariable("userGroupId") String userGroupId, @PathVariable("userId") String userId, Principal principal) {
        Boolean result = userGroupInviteService.denyUserGroupInvitation(Long.valueOf(userGroupId), Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Group invite was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Group invite was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/groupInvite/{userGroupId}/accept")
    public ResponseEntity<MessageResponse> acceptUserGroupInvitation (@PathVariable("userGroupId") String userGroupId,  Principal principal) {
        Boolean result = userGroupInviteService.acceptGroupInvitation(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("You successfully enter to the group!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("You cannot enter to the group, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/groupInvite/{userGroupId}/accept/{userId}")
    public ResponseEntity<UserGroupPairForUserDTO> acceptUserGroupInvitation (@PathVariable("userGroupId") String userGroupId, @PathVariable("userId") String userId, Principal principal) {
        User user = utilsService.getUserById(Long.valueOf(userId));
        Boolean result = userGroupInviteService.acceptGroupInvitation(Long.valueOf(userGroupId), Long.valueOf(userId), principal);
        if (result) {
            UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(Long.valueOf(userGroupId), Long.valueOf(userId));
            UserGroupPairForUserDTO userGroupPairForUserDTO = userGroupPairFacade.userGroupPairToUserGroupPairForUserDTO(userGroupPair,user);
            return new ResponseEntity<>(userGroupPairForUserDTO, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/groupPair/{userGroupId}/delete")
    public ResponseEntity<MessageResponse> endGroupMembership (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        Boolean result = userGroupInviteService.endGroupMembership(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Group membership was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Group membership was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/groupPair/{userGroupId}/makeAdmin/{userId}")
    public ResponseEntity<MessageResponse> makeNewAdmin (@PathVariable("userGroupId") String userGroupId, @PathVariable("userId") String userId, Principal principal) {
        System.out.println("public ResponseEntity<MessageResponse> makeNewAdmin (@PathVariable(\"userGroupId\") String userGroupId, @PathVariable(\"userId\") String userId, Principal principal) {");
        Boolean result = userGroupInviteService.makeNewGroupAdmin(Long.valueOf(userGroupId), Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("New admin was created"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("New admin was not created, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/groupPair/{userGroupId}/kickUserFromUserGroup/{userId}")
    public ResponseEntity<MessageResponse> kickUserFromUserGroup (@PathVariable("userGroupId") String userGroupId, @PathVariable("userId") String userId, Principal principal) {
        System.out.println("public ResponseEntity<MessageResponse> kickUserFromUserGroup (@PathVariable(\"userGroupId\") String userGroupId, @PathVariable(\"userId\") String userId, Principal principal) {");
        Boolean result = userGroupInviteService.kickUserFromGroup(Long.valueOf(userGroupId), Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("User kicked from group!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("User was not kicked from group, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    //!!!!!!
    @PostMapping("/enterToGroup/{userGroupId}")
    public ResponseEntity<MessageResponse> enterToGroup (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        Boolean result = userGroupInviteService.enterToGroup(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Successfully enter to group"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Doesn't enter to group, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    //endGroupAdmin(Long userGroupId, Principal principal)
    @PatchMapping("/groupPair/{userGroupId}/endAdmin")
    public ResponseEntity<MessageResponse> endAdmin (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        Boolean result = userGroupInviteService.endGroupAdmin(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Group adminShip was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Group adminShip was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/groupPair/{userGroupId}/isAdmin")
    public ResponseEntity<MessageResponse> isAdmin (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        Boolean result = userGroupInviteService.isGroupAdmin(Long.valueOf(userGroupId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("User is admin"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("User is not admin"), HttpStatus.BAD_REQUEST);
        }
    }
}
