package com.example.demo.controller;

import com.example.demo.dto.UserNameDTO;
import com.example.demo.entity.UserLike;
import com.example.demo.facade.UserNameFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/like")
@CrossOrigin
public class LikeController {

    private final UserLikeService userLikeService;
    private final UserNameFacade userNameFacade;

    @Autowired
    public LikeController(UserLikeService userLikeService,
                          UserNameFacade userNameFacade
    ) {
        this.userLikeService = userLikeService;
        this.userNameFacade = userNameFacade;
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<UserNameDTO>> getAllLikesForPost(@PathVariable("postId") String postId) {
        List<UserLike> userToPostLikeList = userLikeService.getUserLikesFromPost(Long.valueOf(postId));
        List<UserNameDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToPostLike : userToPostLikeList) {
            userAuthorDTOList.add(userNameFacade.userToUserNameDTO(userToPostLike.getUser()));
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<MessageResponse> likePost (@PathVariable("postId") String postId, Principal principal) {
        Boolean result = userLikeService.likePost(Long.valueOf(postId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Post was liked!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Post was not liked, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<MessageResponse> dislikePost (@PathVariable("postId") String postId, Principal principal) {
        Boolean result = userLikeService.disLikePost(Long.valueOf(postId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Post like was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Post like was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

}
