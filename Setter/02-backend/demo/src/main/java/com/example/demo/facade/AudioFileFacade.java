package com.example.demo.facade;

import com.example.demo.dto.AudioFileDTO;
import com.example.demo.entity.AudioFile;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.service.UserLikeService;
import com.example.demo.service.audioServices.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AudioFileFacade {
    private final AudioService audioFileService;
    private final UserLikeService userLikeService;
    private final UserAuthorFacade userAuthorFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;

    @Autowired
    public AudioFileFacade(AudioService audioFileService,
                           UserLikeService userLikeService,
                           UserAuthorFacade userAuthorFacade,
                           UserGroupAuthorFacade userGroupAuthorFacade
    ) {
        this.audioFileService = audioFileService;
        this.userLikeService = userLikeService;
        this.userAuthorFacade = userAuthorFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
    }




    public AudioFileDTO audioFileToAudioFileDTO (AudioFile audioFile, User loginUser) {
        AudioFileDTO audioFileDTO = new AudioFileDTO();
        audioFileDTO.setId(audioFile.getId());
        audioFileDTO.setStreamUrl("http://localhost:8080/api/stream/audioStream/" + audioFile.getId());
        audioFileDTO.setDescription(audioFile.getDescription());
        audioFileDTO.setContentType(audioFile.getContentType());
        audioFileDTO.setCreatedDate(audioFile.getCreatedDate());
        audioFileDTO.setLikes(audioFile.getLikes());
        audioFileDTO.setLoginUserAuthor(audioFileService.isUserAuthor(audioFile, loginUser));
        audioFileDTO.setLikedByLoginUser(userLikeService.isUserLikeAudioFile(loginUser, audioFile));

        User user = null;
        UserGroup userGroup = null;
        try {
            user = audioFile.getUser();
            userGroup = audioFile.getUserGroup();
        } catch (Exception e) {}

        if (userGroup != null) {
            audioFileDTO.setUserGroupAuthor(userGroupAuthorFacade.userGroupToUserGroupAuthorDTO(userGroup));
        }
        if (user != null) {
            audioFileDTO.setUserAuthor(userAuthorFacade.userToUserAuthorDTO(user));
        }

        /*
        commentDTO.setUserAuthor(userAuthorFacade.userToUserAuthorDTO(comment.getUser()));
        if (comment.getPost() != null) {
        commentDTO.setPostId(comment.getPost().getId());
        }
        if (comment.getCommonImage() != null) {
        commentDTO.setCommonImageId(comment.getCommonImage().getId());
        }
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setLikedByLoginUser(userLikeService.isUserLikeComment(loginUser, comment));
        */

        return audioFileDTO;
    }
}
