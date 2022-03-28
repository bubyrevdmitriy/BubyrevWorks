package com.example.demo.controller;

import com.example.demo.dto.UserFriendInviteDTO;
import com.example.demo.dto.UserFriendPairDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendInvite;
import com.example.demo.entity.UserFriendPair;
import com.example.demo.facade.UserFriendInviteFacade;
import com.example.demo.facade.UserFriendPairFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.UserFriendService;
import com.example.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/friend")
@CrossOrigin
public class FriendInviteController {

    private final UserFriendService userFriendService;
    private final UserFriendInviteFacade userFriendInviteFacade;
    private final UserFriendPairFacade userFriendPairFacade;
    private final UtilsService utilsService;

    @Autowired
    public FriendInviteController(UserFriendService userFriendService,
                                  UserFriendInviteFacade userFriendInviteFacade,
                                  UserFriendPairFacade userFriendPairFacade,
                                  UtilsService utilsService
    ) {
        this.userFriendService = userFriendService;
        this.userFriendInviteFacade = userFriendInviteFacade;
        this.userFriendPairFacade = userFriendPairFacade;
        this.utilsService = utilsService;
    }

    @GetMapping("/friendInvite/user/send/{userId}")
    public ResponseEntity<List<UserFriendInviteDTO>> getUserFriendInviteSendForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserFriendInvite> userFriendInviteSendList = userFriendService.getUserFriendInvitesSendFromUser(Long.valueOf(userId));
        List<UserFriendInviteDTO> userFriendInviteSendDTO = new ArrayList<>();
        for (UserFriendInvite friendInvite : userFriendInviteSendList) {
            userFriendInviteSendDTO.add(userFriendInviteFacade.userFriendInviteToUserFriendInviteDTO(friendInvite, hostUser));
        }
        return new ResponseEntity<>(userFriendInviteSendDTO, HttpStatus.OK);
    }

    @GetMapping("/friendInvite/user/received/{userId}")
    public ResponseEntity<List<UserFriendInviteDTO>> getUserFriendInviteReceivedForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserFriendInvite> userFriendInviteRecievedList = userFriendService.getUserFriendInvitesReceivedFromUser(Long.valueOf(userId), principal);
        List<UserFriendInviteDTO> userFriendInviteRecievedDTOList = new ArrayList<>();
        for (UserFriendInvite friendInvite : userFriendInviteRecievedList) {
            userFriendInviteRecievedDTOList.add(userFriendInviteFacade.userFriendInviteToUserFriendInviteDTO(friendInvite, hostUser));
        }
        return new ResponseEntity<>(userFriendInviteRecievedDTOList, HttpStatus.OK);
    }

    @GetMapping("/friendPair/user/all/{userId}")
    public ResponseEntity<List<UserFriendPairDTO>> getUserPairsForUser(@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserById(Long.valueOf(userId));
        List<UserFriendPair> userFriendPairList = userFriendService.getUserFriendPairsFromUser(Long.valueOf(userId));
        List<UserFriendPairDTO> userFriendPairDTOList = new ArrayList<>();
        for (UserFriendPair friendPair : userFriendPairList) {
            userFriendPairDTOList.add(userFriendPairFacade.userFriendPairToUserFriendPairDTO(friendPair,hostUser));
        }
        return new ResponseEntity<>( userFriendPairDTOList, HttpStatus.OK);
    }

    @PostMapping("/friendInvite/create/{userId}")
    public ResponseEntity<MessageResponse> inviteUserToMyFriend (@PathVariable("userId") String userId, Principal principal) {
        Boolean result = userFriendService.inviteUserToMyFriend(Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Invite to friends was created"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Invite to friends was not created, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/friendInvite/deny/{userId}")
    public ResponseEntity<MessageResponse> denyInvitation (@PathVariable("userId") String userId, Principal principal) {
        Boolean result = userFriendService.denyInvitation(Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Invite to friends was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Invite to friends was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/friendInvite/accept/{userId}")
    public ResponseEntity<UserFriendPairDTO> acceptInvitation (@PathVariable("userId") String userId, Principal principal) {
        User hostUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(Long.valueOf(userId));
        Boolean result = userFriendService.acceptInvitation(Long.valueOf(userId), principal);
        if (result) {
            UserFriendPair friendPair = userFriendService.getFriendPairByUsers(otherUser, hostUser);
            UserFriendPairDTO userFriendPairDTO = userFriendPairFacade.userFriendPairToUserFriendPairDTO(friendPair, hostUser);
            return new ResponseEntity<>(userFriendPairDTO, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/friendPair/delete/{userId}")
    public ResponseEntity<MessageResponse> endFriendship (@PathVariable("userId") String userId, Principal principal) {
        Boolean result = userFriendService.endFriendship(Long.valueOf(userId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Friendship was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Friendship was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }
}
