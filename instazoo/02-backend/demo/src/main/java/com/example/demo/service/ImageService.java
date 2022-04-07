package com.example.demo.service;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.ImageNotFoundException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.path.common.images}")
    private String uploadPath;

    public static final Logger LOG = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;
    private final UserService userService;
    private final UtilsService utilsService;
    private final PostService postService;

    @Autowired
    public ImageService(ImageRepository imageRepository,
                        UtilsService utilsService,
                        UserService userService,
                        PostService postService
    ) {
        this.imageRepository = imageRepository;
        this.utilsService = utilsService;
        this.userService = userService;
        this.postService = postService;
    }

    public ImageModel uploadImageToPost(MultipartFile file, Long postId, Principal principal) throws IOException {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            Post post = postService.getPostById(postId);
            ImageModel savedImage = saveImage(file, post, user);
            System.out.println("!!11!!11!!");
            System.out.println(savedImage.getName());
            return savedImage;
        } catch (Exception e) {
            System.out.println("!!22!!22!!");
            e.printStackTrace();
            return null;
        }
    }

    public ImageModel uploadImageToUser(MultipartFile file, Principal principal) throws IOException {
        try {
            User user = utilsService.getUserByPrincipal(principal);
            ImageModel savedImage = saveImage(file, null, user);
            System.out.println(savedImage.getName());
            userService.updateUserProfilePhoto(savedImage, user);
            System.out.println("!!11!!11!!");
            System.out.println(user.getProfileImage().getName());
            return savedImage;
        } catch (Exception e) {
            System.out.println("!!22!!22!!");
            e.printStackTrace();
            return null;
        }
    }

    public ImageModel getImageToUser(User user) {
        return user.getProfileImage();
    }

    public List<ImageModel> getImageToPost(Post post){
        return imageRepository.findByPost(post);
    }

    public ImageModel getImageModelById (Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Cannot be found image with id: " + imageId));
    }

    private ImageModel saveImage(MultipartFile file, Post post, User user) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            ImageModel imageModel = new ImageModel();
            imageModel.setName(resultFilename);
            imageModel.setAddress(uploadPath + "/" + resultFilename);
            imageModel.setUser(user);
            if(post != null) {
                imageModel.setPost(post);
            }



            LOG.info("Saving image for User: {}", user.getEmail());
            ImageModel savedImage = null;
            try {
                savedImage = imageRepository.save(imageModel);
                System.out.println("MultipartFile file, Post post, User user true");
            } catch (Exception e) {
                System.out.println("MultipartFile file, Post post, User user false");
                e.printStackTrace();
            }

            return savedImage;
        }
        return null;
    }


}
