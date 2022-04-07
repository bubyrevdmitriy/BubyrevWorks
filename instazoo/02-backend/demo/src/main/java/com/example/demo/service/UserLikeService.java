package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.UserLike;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserLikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserLikeService {
    public static final Logger LOG = LoggerFactory.getLogger(UserLikeService.class);

    private final PostService postService;
    private final UserLikeRepository userLikeRepository;
    private final PostRepository postRepository;
    private final UtilsService utilsService;

    @Autowired
    public UserLikeService(PostService postService,
                           UserLikeRepository userLikeRepository,
                           PostRepository postRepository,
                           UtilsService utilsService
    ) {
        this.postService = postService;
        this.userLikeRepository = userLikeRepository;
        this.postRepository = postRepository;
        this.utilsService = utilsService;
    }

         public Boolean likePost(Long postId, Principal principal) {
         try {
             User user = utilsService.getUserByPrincipal(principal);
             Post post = postService.getPostById(postId);
             if (!isUserLikePost(user, post)) {
                 UserLike userLike = new UserLike();
                 userLike.setUser(user);
                 userLike.setPost(post);
                 if (post.getLikes() == null) {
                     post.setLikes(1);
                 } else {
                     post.setLikes(post.getLikes() + 1);
                 }
                 postRepository.save(post);
                 userLikeRepository.save(userLike);
                 LOG.info("Saving Like for User: {}. And post: {}", user.getId(), post.getId());
                 return true;
             } else {
                 return false;
             }
         } catch (Exception e) {
             return false;
         }
     }

    public Boolean disLikePost(Long postId, Principal principal) {
         try {
             User user = utilsService.getUserByPrincipal(principal);
             Post post = postService.getPostById(postId);
             if (isUserLikePost(user, post)) {
                 UserLike userToPostLike = getUserLikeByUserAndPost(user, post);
                 if (userToPostLike != null) {
                     post.setLikes(post.getLikes() - 1);
                     postRepository.save(post);
                     userLikeRepository.delete(userToPostLike);
                     LOG.info("Delete Like for User: {}. And post: {}", user.getId(), post.getId());
                     return true;
                 }
             } else {
                 return false;
             }
         } catch (Exception e) {
         }
        return false;
    }

    public List<UserLike> getUserLikesFromPost(Long postId) {
        Post post = postService.getPostById(postId);
        return userLikeRepository.findAllByPost(post);
    }

    public UserLike getUserLikeByUserAndPost(User user, Post post) {
        return userLikeRepository.findByUserAndPost(user, post)
                .orElse(null);
    }

    public boolean isUserLikePost(User user, Post post) {
        UserLike userLike = getUserLikeByUserAndPost(user, post);
        return userLike != null;
    }

}
