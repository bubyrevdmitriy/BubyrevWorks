package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.CommonImageNotFoundException;
import com.example.demo.repository.CommonImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserGroupRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommonImageService {

    @Value("${upload.path.common.images}")
    private String uploadPath;

    public static final Logger LOG = LoggerFactory.getLogger(CommonImageService.class);

    private final CommonImageRepository commonImageRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserGroupRepository userGroupRepository;
    private final PostService postService;
    private final UtilsService utilsService;
    private final UserGroupService userGroupService;
    private final UserGroupInviteService userGroupInviteService;

    @Autowired
    public CommonImageService(CommonImageRepository commonImageRepository,
                              UserRepository userRepository,
                              PostRepository postRepository,
                              UserGroupRepository userGroupRepository,
                              PostService postService,
                              UtilsService utilsService,
                              UserGroupService userGroupService,
                              UserGroupInviteService userGroupInviteService
    ) {
        this.commonImageRepository = commonImageRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userGroupRepository = userGroupRepository;
        this.postService = postService;
        this.utilsService = utilsService;
        this.userGroupService = userGroupService;
        this.userGroupInviteService = userGroupInviteService;
    }

    public CommonImage uploadImageToUser(MultipartFile file, Long postId, Principal principal) throws IOException {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = postService.getPostById(postId);

        CommonImage savedImage = saveImage(file, post, user);
        user.setProfileImageId(savedImage.getId());
        userRepository.save(user);
        return savedImage;
    }

    public CommonImage uploadImageToPost(MultipartFile file, Long postId, Principal principal) throws IOException {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = postService.getPostById(postId);
        UserGroup userGroup;
        UserGroupPair userGroupPair;
        CommonImage savedImage = null;
        try {
            userGroup = userGroupService.getUserGroupById(post.getUserGroup().getId());
            userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
            if (userGroupPair.getIsAdmin()) {
                // пользователь может добавлять фото только к своим постам
                savedImage = saveImageToUserGroup(file, post, userGroup, user);
            }
        } catch (Exception e) {
            savedImage = saveImage(file, post, user);
        }
        return savedImage;
    }

    public CommonImage uploadImageToUserGroup(MultipartFile file, Long userGroupId, Long postId, Principal principal) throws IOException {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = postService.getPostById(postId);
        UserGroup userGroup;
        UserGroupPair userGroupPair;

        try {
            userGroup = userGroupService.getUserGroupById(userGroupId);
            userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
            if (userGroupPair.getIsAdmin()) {
                CommonImage savedImage = saveImageToUserGroup(file, post, userGroup, user);
                userGroup.setProfileImageId(savedImage.getId());
                userGroupRepository.save(userGroup);
                return savedImage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CommonImage getProfileImageToUser(User user) {
        return commonImageRepository.findById(user.getProfileImageId()).orElse(null);
    }

    public CommonImage getProfileImageToUserGroup(UserGroup userGroup) {
        return commonImageRepository.findById(userGroup.getProfileImageId()).orElse(null);
    }


    public CommonImage getImageToUserId(Long userId, Principal principal) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user == null) {
            return null;
        }
        return commonImageRepository.findById(user.getProfileImageId()).orElse(null);
    }

    public List<CommonImage> getImageToPost(Post post) {
        return commonImageRepository.findByPost(post);
    }

    public List<CommonImage> getImagesToUser(Long userId) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return commonImageRepository.findByUserAndUserGroupOrderByCreatedDateDesc(user, null);
        }
    }

    public List<CommonImage> getImagesToUser(User user) {
        if (user == null) {
            return null;
        } else {
            return commonImageRepository.findByUserOrderByCreatedDateDesc(user);
        }
    }

    public List<CommonImage> getImagesToUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return commonImageRepository.findByUserGroupOrderByCreatedDateDesc(userGroup);
        }
    }

    public List<CommonImage> getImagesToUser(Long userId, Pageable pageable) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return commonImageRepository.findByUserAndUserGroupOrderByCreatedDateDesc(user, null,pageable);
        }
    }

    public List<CommonImage> getImagesToUserGroup(Long userGroupId, Pageable pageable) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return commonImageRepository.findByUserGroupOrderByCreatedDateDesc(userGroup, pageable);
        }
    }

    public Long countImagesToUser(Long userId) {
        User user = utilsService.getUserById(userId);
        if (user == null) {
            return null;
        } else {
            return commonImageRepository.countByUserAndUserGroup(user, null);
        }
    }

    public Long countImagesToUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        if (userGroup == null) {
            return null;
        } else {
            return commonImageRepository.countByUserGroup(userGroup);
        }
    }

    public List<CommonImage> getImagesToUserGroup(UserGroup userGroup) {
        if (userGroup == null) {
            return null;
        } else {
            return commonImageRepository.findByUserGroupOrderByCreatedDateDesc(userGroup);
        }
    }

    public CommonImage getCommonImageById (Long imageId) {
        return commonImageRepository.findById(imageId)
                .orElseThrow(() -> new CommonImageNotFoundException("Cannot be found image with id: " + imageId));
    }

    @Transactional
    public void deleteImage(Long imageId, Principal principal) {
        CommonImage commonImage = getCommonImageById(imageId);
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup;
        UserGroupPair userGroupPair;
        try {
            userGroup = commonImage.getUserGroup();
            userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);

            if (userGroupPair.getIsAdmin()) {

                try {
                    // retrieve image
                    Files.delete(Path.of(commonImage.getAddress()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                commonImageRepository.delete(commonImage);
            }
        } catch (Exception e) {
            if (user.getId().equals(commonImage.getUser().getId())) {
                try {
                    // retrieve image
                    Files.delete(Path.of(commonImage.getAddress()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                commonImageRepository.delete(commonImage);
            }
        }
    }

    private CommonImage saveImage(MultipartFile file, Post post, User user) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            CommonImage commonImage = new CommonImage();
            commonImage.setName(resultFilename);
            commonImage.setAddress(uploadPath + "/" + resultFilename);
            commonImage.setLikes(0);
            commonImage.setUser(user);
            commonImage.setPost(post);
            commonImage.setComments(new ArrayList<Comment>());

            LOG.info("Saving image for User: {}", user.getEmail());
            return commonImageRepository.save(commonImage);
        }
        return null;
    }


    private CommonImage saveImageToUserGroup(MultipartFile file, Post post, UserGroup userGroup, User user) {
        try {
            CommonImage commonImage = saveImage(file, post, user);
            commonImage.setUserGroup(userGroup);
            return commonImageRepository.save(commonImage);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public CommonImage getPreviousCommonImageByCommonImageId(Long imageId) {
        CommonImage currentCommonImage = getCommonImageById(imageId);
        User user;
        UserGroup userGroup;
        try {
            userGroup = currentCommonImage.getUserGroup();
            List<CommonImage> commonImageList = getImagesToUserGroup(userGroup);
            CommonImage[] array = commonImageList.toArray(new CommonImage[0]);
        } catch (Exception e) {
            user = currentCommonImage.getUser();
            List<CommonImage> commonImageList = getImagesToUser(user);
            CommonImage[] array = commonImageList.toArray(new CommonImage[0]);
        }

        return null;
    }

    public CommonImage getNextCommonImageByCommonImageId(Long imageId) {
        CommonImage currentCommonImage = getCommonImageById(imageId);
        User user;
        UserGroup userGroup;
        try {
            userGroup = currentCommonImage.getUserGroup();
            List<CommonImage> commonImageList = getImagesToUserGroup(userGroup);
            CommonImage[] array = commonImageList.toArray(new CommonImage[0]);
        } catch (Exception e) {
            user = currentCommonImage.getUser();
            List<CommonImage> commonImageList = getImagesToUser(user);
            CommonImage[] array = commonImageList.toArray(new CommonImage[0]);
        }
        return null;
    }

    public boolean isUserAuthor(CommonImage commonImage, User user) {
        UserGroup userGroup;
        UserGroupPair userGroupPair;
        try {
            userGroup = commonImage.getUserGroup();
            userGroupPair = userGroupInviteService.getUserGroupPairByUserAndGroup(user, userGroup);
            if (userGroupPair.getIsAdmin()) {
                return true;
            }
        } catch (Exception e) {
            if (user.getId().equals(commonImage.getUser().getId())) {
                return true;
            }
        }
        return false;
    }
}