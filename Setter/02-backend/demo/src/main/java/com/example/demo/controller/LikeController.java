package com.example.demo.controller;

import com.example.demo.dto.UserAuthorDTO;
import com.example.demo.entity.*;
import com.example.demo.facade.UserAuthorFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.*;
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
    private final UserAuthorFacade userAuthorFacade;

    @Autowired
    public LikeController(UserLikeService userLikeService,
                          UserAuthorFacade userAuthorFacade
    ) {
        this.userLikeService = userLikeService;
        this.userAuthorFacade = userAuthorFacade;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<UserAuthorDTO>> getAllLikesForPost(@PathVariable("postId") String postId) {
        List<UserLike> userToPostLikeList = userLikeService.getUserLikesFromPost(Long.valueOf(postId));
        List<UserAuthorDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToPostLike : userToPostLikeList) {
            userAuthorDTOList.add(userAuthorFacade.userToUserAuthorDTO(userToPostLike.getUser()));
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<List<UserAuthorDTO>> getAllLikesForComment(@PathVariable("commentId") String commentId) {
        List<UserLike> userToCommentLikeList = userLikeService.getUserLikesFromComment(Long.valueOf(commentId));
        List<UserAuthorDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToCommentLike : userToCommentLikeList) {
            userAuthorDTOList.add(userAuthorFacade.userToUserAuthorDTO(userToCommentLike.getUser()));
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @GetMapping("/commonImage/{commonImageId}")
    public ResponseEntity<List<UserAuthorDTO>> getAllLikesForCommonImage(@PathVariable("commonImageId") String commonImageId) {
        List<UserLike> userToCommonImageLikeList = userLikeService.getUserLikesFromCommonImage(Long.valueOf(commonImageId));
        List<UserAuthorDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToCommonImageLike : userToCommonImageLikeList) {
            userAuthorDTOList.add(userAuthorFacade.userToUserAuthorDTO(userToCommonImageLike.getUser()));
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @GetMapping("/audioFile/{audioFileId}")
    public ResponseEntity<List<UserAuthorDTO>> getAllLikesForAudioFile(@PathVariable("audioFileId") String audioFileId) {
        List<UserLike> userToAudioFileLikeList = userLikeService.getUserLikesFromAudioFile(Long.valueOf(audioFileId));
        List<UserAuthorDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToAudioFileLike : userToAudioFileLikeList) {
            userAuthorDTOList.add(userAuthorFacade.userToUserAuthorDTO(userToAudioFileLike.getUser()));
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }

    @GetMapping("/videoFile/{videoFileId}")
    public ResponseEntity<List<UserAuthorDTO>> getAllLikesForVideoFile(@PathVariable("videoFileId") String videoFileId) {
        List<UserLike> userToVideoFileLikeList = userLikeService.getUserLikesFromVideoFile(Long.valueOf(videoFileId));
        List<UserAuthorDTO> userAuthorDTOList = new ArrayList<>();
        for (UserLike userToVideoFileLike : userToVideoFileLikeList) {
            userAuthorDTOList.add(userAuthorFacade.userToUserAuthorDTO(userToVideoFileLike.getUser()));
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

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<MessageResponse> likeComment (@PathVariable("commentId") String commentId, Principal principal) {
        Boolean result = userLikeService.likeComment(Long.valueOf(commentId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Comment was liked!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Comment was not liked, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/commonImage/{commonImageId}")
    public ResponseEntity<MessageResponse> likeCommonImage (@PathVariable("commonImageId") String commonImageId, Principal principal) {
        Boolean result = userLikeService.likeCommonImage(Long.valueOf(commonImageId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Image was liked!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Image was not liked, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/audioFile/{audioFileId}")
    public ResponseEntity<MessageResponse> likeAudioFile (@PathVariable("audioFileId") String audioFileId, Principal principal) {
        Boolean result = userLikeService.likeAudioFile(Long.valueOf(audioFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("AudioFile was liked!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("AudioFile was not liked, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/videoFile/{videoFileId}")
    public ResponseEntity<MessageResponse> likeVideoFile (@PathVariable("videoFileId") String videoFileId, Principal principal) {
        Boolean result = userLikeService.likeVideoFile(Long.valueOf(videoFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("VideoFile was liked!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("VideoFile was not liked, some error occurred"), HttpStatus.BAD_REQUEST);
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

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<MessageResponse> disLikeComment (@PathVariable("commentId") String commentId, Principal principal) {
        Boolean result = userLikeService.disLikeComment(Long.valueOf(commentId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Comment like was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Comment like was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/commonImage/{commonImageId}")
    public ResponseEntity<MessageResponse> dislikeCommonImage (@PathVariable("commonImageId") String commonImageId, Principal principal) {
        Boolean result = userLikeService.disLikeCommonImage(Long.valueOf(commonImageId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Image like was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Image like was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/audioFile/{audioFileId}")
    public ResponseEntity<MessageResponse> dislikeAudioFile (@PathVariable("audioFileId") String audioFileId, Principal principal) {
        Boolean result = userLikeService.disLikeAudioFile(Long.valueOf(audioFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("AudioFile like was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("AudioFile like was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/videoFile/{videoFileId}")
    public ResponseEntity<MessageResponse> dislikeVideoFile (@PathVariable("videoFileId") String videoFileId, Principal principal) {
        Boolean result = userLikeService.disLikeVideoFile(Long.valueOf(videoFileId), principal);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("VideoFile like was deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("VideoFile like was not deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }
}
