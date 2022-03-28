package com.example.demo.facade;

import com.example.demo.dto.VideoFileDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.VideoFile;
import com.example.demo.service.UserLikeService;
import com.example.demo.service.videoServices.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoFileFacade {
    private final VideoService videoFileService;
    private final UserLikeService userLikeService;
    private final UserAuthorFacade userAuthorFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;

    @Autowired
    public VideoFileFacade(VideoService videoFileService,
                           UserLikeService userLikeService,
                           UserAuthorFacade userAuthorFacade,
                           UserGroupAuthorFacade userGroupAuthorFacade
    ) {
        this.videoFileService = videoFileService;
        this.userLikeService = userLikeService;
        this.userAuthorFacade = userAuthorFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
    }

    public VideoFileDTO videoFileToVideoFileDTO (VideoFile videoFile, User loginUser) {
        VideoFileDTO videoFileDTO = new VideoFileDTO();
        videoFileDTO.setId(videoFile.getId());
        //videoFileDTO.setPreviewUrl("https://loremflickr.com/213/106");
        videoFileDTO.setPreviewUrl("http://localhost:8080/api/video/preview/" + videoFile.getId());//http://localhost:8080//https://loremflickr.com/213/106//
        videoFileDTO.setStreamUrl("http://localhost:8080/api/stream/videoStream/" + videoFile.getId());//http://localhost:8080
        videoFileDTO.setDescription(videoFile.getDescription());
        videoFileDTO.setContentType(videoFile.getContentType());
        videoFileDTO.setCreatedDate(videoFile.getCreatedDate());
        videoFileDTO.setCreatedDate(videoFile.getCreatedDate());
        videoFileDTO.setLikes(videoFile.getLikes());
        videoFileDTO.setLoginUserAuthor(videoFileService.isUserAuthor(videoFile, loginUser));
        videoFileDTO.setLikedByLoginUser(userLikeService.isUserLikeVideoFile(loginUser, videoFile));

        User user = null;
        UserGroup userGroup = null;
        try {
            user = videoFile.getUser();
            userGroup = videoFile.getUserGroup();
        } catch (Exception e) {}

        if (userGroup != null) {
            videoFileDTO.setUserGroupAuthor(userGroupAuthorFacade.userGroupToUserGroupAuthorDTO(userGroup));
        }
        if (user != null) {
            videoFileDTO.setUserAuthor(userAuthorFacade.userToUserAuthorDTO(user));
        }

        return videoFileDTO;
    }

}
