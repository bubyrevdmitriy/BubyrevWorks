package com.example.demo.facade;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.CommonImage;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.service.PostService;
import com.example.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostFacade {

    private final UserAuthorFacade userAuthorFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;
    private final ImageFacade imageFacade;
    private final UserLikeService userLikeService;
    private final PostService postService;

    @Autowired
    public PostFacade(UserAuthorFacade userAuthorFacade,
                      UserGroupAuthorFacade userGroupAuthorFacade,
                      ImageFacade imageFacade,
                      UserLikeService userLikeService,
                      PostService postService) {
        this.userAuthorFacade = userAuthorFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
        this.imageFacade = imageFacade;
        this.userLikeService = userLikeService;
        this.postService = postService;
    }


    public PostDTO postToPostDTO(Post post, User loginUser) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setLikes(post.getLikes());

        User user = null;
        UserGroup userGroup = null;
        try {
            user = post.getUser();
            userGroup = post.getUserGroup();
        } catch (Exception e) {}

        if (userGroup != null) {
            postDTO.setUserGroupAuthor(userGroupAuthorFacade.userGroupToUserGroupAuthorDTO(userGroup));
        }
        if (user != null) {
            postDTO.setUserAuthor(userAuthorFacade.userToUserAuthorDTO(user));
        }

        postDTO.setCreatedDate(post.getCreatedDate());

        List <CommonImage> commonImages = post.getCommonImages();
        List <ImageDTO> imageDTOs = new ArrayList<>();
        for (CommonImage commonImage : commonImages) {
            try {
                imageDTOs.add(imageFacade.imageToImageDTO(commonImage));
            } catch (Exception e) {}
        }
        postDTO.setCommonImages(imageDTOs);

        postDTO.setLikedByLoginUser(userLikeService.isUserLikePost(loginUser, post));

        postDTO.setLoginUserAuthor(postService.isUserAuthor(post, loginUser));
        return postDTO;
    }

    public PostDTO postToPostExtendedDTO(Post post, User loginUser) {

        PostDTO postDTO = postToPostDTO(post, loginUser);

        UserGroup userGroup;
        User user;
        Long previousPostId = null, nextPostId = null;
        List<Post> posts;
        userGroup = post.getUserGroup();
        user = post.getUser();
        if (userGroup != null) {
            posts = postService.getAllPostsFromUserGroup(userGroup.getId());
        } else {
            posts = postService.getAllPostsFromUser(user.getId());
        }
        if (posts.size()>1) {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).equals(post)) {
                    if (posts.get(i).equals(posts.get(0))) {
                        previousPostId = posts.get(posts.size()-1).getId();
                        nextPostId = posts.get(i+1).getId();
                    } else if (posts.get(i).equals(posts.get(posts.size()-1))) {
                        previousPostId = posts.get(i-1).getId();
                        nextPostId = posts.get(0).getId();
                    } else {
                        previousPostId = posts.get(i-1).getId();
                        nextPostId = posts.get(i+1).getId();
                    }
                    postDTO.setPreviousPostId(previousPostId);
                    postDTO.setNextPostId(nextPostId);
                }
            }
        }
        return postDTO;
    }
}
