package com.example.demo.facade;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostFacade {

    private final UserLikeService userLikeService;
    private final ImageFacade imageFacade;
    private final UserNameFacade userNameFacade;
    private final ImageService imageService;

    @Autowired
    public PostFacade(UserLikeService userLikeService,
                      ImageFacade imageFacade,
                      UserNameFacade userNameFacade,
                      ImageService imageService
    ) {
        this.userLikeService = userLikeService;
        this.imageFacade = imageFacade;
        this.userNameFacade = userNameFacade;
        this.imageService = imageService;
    }

    public PostDTO postToPostDTO(Post post, User loginUser) throws IOException {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setLikes(post.getLikes());
        postDTO.setLocation(post.getLocation());
        postDTO.setTitle(post.getTitle());
        if (post.getUser().getId().equals(loginUser.getId())) {
            postDTO.setLoginUserAuthor(true);
        } else {
            postDTO.setLoginUserAuthor(false);
        }
        postDTO.setLikedByLoginUser(userLikeService.isUserLikePost(loginUser, post));
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setAuthor(userNameFacade.userToUserNameDTO(post.getUser()));

        List<ImageModel> imageModelList = imageService.getImageToPost(post);
        List<ImageDTO> imageDTOList = new ArrayList<>();
        for (ImageModel imageModel : imageModelList) {
            imageDTOList.add(imageFacade.imageToImageDTO(imageModel));
        }
        postDTO.setImages(imageDTOList);
        return postDTO;
    }
}
