package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.CommonImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    public static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;
    private final CommonImageRepository commonImageRepository;
    private final UtilsService utilsService;
    private final UserGroupService userGroupService;
    private final UserGroupInviteService userGroupInviteService;

    @Autowired
    public PostService(PostRepository postRepository,
                       CommonImageRepository commonImageRepository,
                       UtilsService utilsService,
                       UserGroupService userGroupService,
                       UserGroupInviteService userGroupInviteService
    ) {
        this.postRepository = postRepository;
        this.commonImageRepository = commonImageRepository;
        this.utilsService = utilsService;
        this.userGroupService = userGroupService;
        this.userGroupInviteService = userGroupInviteService;
    }

    public Post createPost(PostDTO postDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = new Post();
        post.setCaption(postDTO.getCaption());
        post.setLikes(0);
        post.setUser(user);
        post.setCommonImages(new ArrayList<CommonImage>());
        post.setComments(new ArrayList<Comment>());

        LOG.info("Saving Post for User: {}", user.getEmail());
        return postRepository.save(post);
    }

    public Post createPost(PostDTO postDTO, Long userGroupId, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);

        if (userGroupPair !=null && userGroupPair.getIsAdmin()) {
            Post post = new Post();
            post.setCaption(postDTO.getCaption());
            post.setLikes(0);
            post.setUser(user);
            post.setUserGroup(userGroup);
            post.setCommonImages(new ArrayList<CommonImage>());
            post.setComments(new ArrayList<Comment>());

            LOG.info("Saving Post for User: {}", user.getEmail());
            return postRepository.save(post);
        }
        return null;
    }

    public Post createPostFromString(String caption, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = new Post();
        post.setCaption(caption);
        post.setLikes(0);
        post.setUser(user);
        post.setCommonImages(new ArrayList<CommonImage>());
        post.setComments(new ArrayList<Comment>());

        LOG.info("Saving Post for User: {}", user.getEmail());
        return postRepository.save(post);
    }

    public Post createPostFromString(String caption, Long userGroupId, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);

        if (userGroupPair !=null && userGroupPair.getIsAdmin()) {
            Post post = new Post();
            post.setCaption(caption);
            post.setLikes(0);
            post.setUserGroup(userGroup);
            post.setCommonImages(new ArrayList<CommonImage>());
            post.setComments(new ArrayList<Comment>());

            LOG.info("Saving Post for User: {}", user.getEmail());
            return postRepository.save(post);
        }
        return null;
    }

    public List<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedDateDesc(pageable);
    }
    public Long countAllPosts() {return postRepository.count();}

    public List<Post> searchPosts(String searchValue, Pageable pageable) {
        return postRepository.findByCaptionContainingOrderByCreatedDateDesc(searchValue, pageable);
    }
    public Long countSearchPosts(String searchValue) {
        return postRepository.countByCaptionContaining(searchValue);
    }

    public  List<Post> getAllPostsFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return postRepository.findByUserAndUserGroupOrderByCreatedDateDesc(user, null);
    }
    public  List<Post> getAllPostsFromUser(Long userId, Pageable pageable) {
        User user = utilsService.getUserById(userId);
        return postRepository.findByUserAndUserGroupOrderByCreatedDateDesc(user, null, pageable);
    }
    public Long countPostsToUser(Long userId) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return postRepository.countByUserAndUserGroup(user, null);
        }
    }

    public  List<Post> getAllPostsFromUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return postRepository.findByUserGroupOrderByCreatedDateDesc(userGroup);
    }
    public  List<Post> getAllPostsFromUserGroup(Long userGroupId, Pageable pageable) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return postRepository.findByUserGroupOrderByCreatedDateDesc(userGroup, pageable);
    }
    public Long countPostsToUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return postRepository.countByUserGroup(userGroup);
        }
    }

    public Post getPostById (Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Cannot find post with id: " + postId));
    }

    //@Transactional
    public void savePostFromUser(Post post, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);

        if (user.getId().equals(post.getUser().getId())) {
            postRepository.save(post);
        }
    }

    public boolean isUserAuthor(Post post, User user) {
        UserGroup userGroup = post.getUserGroup();
        if (userGroup != null) {
            UserGroupPair userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
            if (userGroupPair != null) {
                return userGroupPair.getIsAdmin();
            }
            return false;
        }
        return user.getId().equals(post.getUser().getId());
    }

    @Transactional
    public void deletePost(Long postId, Principal principal) {
        Post post = getPostById(postId);
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup;
        UserGroupPair userGroupPair;
        try {
            userGroup = post.getUserGroup();
            userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);

            if (userGroupPair.getIsAdmin()) {
                ArrayList<CommonImage> commonImages = (ArrayList<CommonImage>) commonImageRepository.findByPost(post);
                for (CommonImage commonImage: commonImages) {
                    commonImage.setPost(null);
                    commonImageRepository.save(commonImage);
                }
                post.setCommonImages(null);
                postRepository.delete(post);
            }
        } catch (Exception e) {
            if (user.getId().equals(post.getUser().getId())) {
                ArrayList<CommonImage> commonImages = (ArrayList<CommonImage>) commonImageRepository.findByPost(post);
                for (CommonImage commonImage: commonImages) {
                    commonImage.setPost(null);
                    commonImageRepository.save(commonImage);
                }
                post.setCommonImages(null);
                postRepository.delete(post);
            }
        }
    }
}
