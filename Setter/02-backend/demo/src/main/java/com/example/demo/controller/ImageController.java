package com.example.demo.controller;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.ImageInAlbumDTO;
import com.example.demo.entity.CommonImage;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.facade.ImageFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageController {

    private final CommonImageService commonImageService;
    private final PostService postService;
    private final ImageFacade imageFacade;
    private final SmallAvatarUserService smallAvatarUserService;
    private final SmallAvatarGroupService smallAvatarGroupService;
    private final UtilsService utilsService;

    @Autowired
    public ImageController(CommonImageService commonImageService,
                           PostService postService,
                           ImageFacade imageFacade,
                           SmallAvatarUserService smallAvatarUserService,
                           SmallAvatarGroupService smallAvatarGroupService,
                           UtilsService utilsService
    ) {
        this.commonImageService = commonImageService;
        this.postService = postService;
        this.imageFacade = imageFacade;
        this.smallAvatarUserService = smallAvatarUserService;
        this.smallAvatarGroupService = smallAvatarGroupService;
        this.utilsService = utilsService;
    }

    @PostMapping("/userPhoto")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal
    ) throws IOException {
        String text = "My new photo!";
        Post post = postService.createPostFromString(text, principal);
        CommonImage commonImage = commonImageService.uploadImageToUser(file, post.getId(), principal);
        System.out.println(commonImage.getAddress());
        smallAvatarUserService.saveSmallAvatar(commonImage.getAddress(), principal);


        //ArrayList<CommonImage> commonImages = new ArrayList<CommonImage>();
        //commonImages.add(commonImage);
        //post.setCommonImages(commonImages);
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    //uploadImageToUserGroup(MultipartFile file, Long userGroupId, Long postId, Principal principal)
    //createPostFromString(String caption, Long userGroupId, Principal principal)
    @PostMapping("/group/{userGroupId}/userGroupPhoto")
    public ResponseEntity<MessageResponse> uploadImageToUserGroup(@RequestParam("file") MultipartFile file,
                                                                  @PathVariable("userGroupId") String userGroupId,
                                                             Principal principal
    ) throws IOException {
        String text = "Our group new photo!";
        Post post = postService.createPostFromString(text, Long.valueOf(userGroupId), principal);
        CommonImage commonImage = commonImageService.uploadImageToUserGroup(file, Long.valueOf(userGroupId), post.getId(), principal);
        smallAvatarGroupService.saveSmallAvatarGroup(commonImage.getAddress(), Long.valueOf(userGroupId));
        ArrayList<CommonImage> commonImages = new ArrayList<CommonImage>();
        commonImages.add(commonImage);
        post.setCommonImages(commonImages);
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    //getImagesToUserGroup(Long userGroupId)
    @GetMapping("/group/{userGroupId}")
    public ResponseEntity<List<ImageInAlbumDTO>> getAllImagesForUserGroup(@PathVariable("userGroupId") String userGroupId,
                                                                          @RequestParam(required = false) String page,
                                                                          @RequestParam(required = false) String size,
                                                                          Principal principal
    ) throws IOException {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        List<ImageInAlbumDTO> imageInAlbumDTOList = new ArrayList<>();
        User loginUser = utilsService.getUserByPrincipal(principal);
        for (CommonImage commonImage : commonImageService.getImagesToUserGroup(Long.valueOf(userGroupId), pageable)) {
            ImageInAlbumDTO imageInAlbumDTO = imageFacade.imageToImageInAlbumDTO(commonImage, loginUser);
            imageInAlbumDTOList.add(imageInAlbumDTO);
        }
        return new ResponseEntity<>(imageInAlbumDTOList, HttpStatus.OK);
    }

    @GetMapping("/group/count/{userGroupId}")
    public ResponseEntity<Long> countAllImagesForUserGroup(@PathVariable("userGroupId") String userGroupId) throws IOException {
        Long imageAmount = commonImageService.countImagesToUserGroup(Long.valueOf(userGroupId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/user/count/{userId}")
    public ResponseEntity<Long> countAllImagesForUser(@PathVariable("userId") String userId,
                                                                     Principal principal
    ) throws IOException {
        Long imageAmount = commonImageService.countImagesToUser(Long.valueOf(userId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ImageInAlbumDTO>> getAllImagesForUser(@PathVariable("userId") String userId,
                                                                     @RequestParam(required = false) String page,
                                                                     @RequestParam(required = false) String size,
                                                                     Principal principal
    ) throws IOException {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<ImageInAlbumDTO> imageInAlbumDTOList = new ArrayList<>();
        for (CommonImage commonImage : commonImageService.getImagesToUser(Long.valueOf(userId), pageable)) {
            ImageInAlbumDTO imageInAlbumDTO = imageFacade.imageToImageInAlbumDTO(commonImage, loginUser);
            imageInAlbumDTOList.add(imageInAlbumDTO);
        }
        return new ResponseEntity<>(imageInAlbumDTOList, HttpStatus.OK);
    }



    @PostMapping("/{postId}/upload")
    public ResponseEntity<ImageDTO> uploadImageToPost(@PathVariable("postId") String postId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal
    ) throws IOException {
        CommonImage commonImage = commonImageService.uploadImageToPost(file, Long.parseLong(postId), principal);
        ImageDTO imageDTO = imageFacade.imageToImageDTO(commonImage);
        return new ResponseEntity<>(imageDTO, HttpStatus.OK);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageInAlbumDTO> getImageById(@PathVariable("imageId") String imageId, Principal principal) throws IOException {
        User loginUser = utilsService.getUserByPrincipal(principal);
        CommonImage commonImage = commonImageService.getCommonImageById(Long.valueOf(imageId));
        ImageInAlbumDTO imageInAlbumDTO = imageFacade.imageToImageInAlbumDTO(commonImage, loginUser);
        return new ResponseEntity<>(imageInAlbumDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{imageId}")
    public ResponseEntity<MessageResponse> deleteImage(@PathVariable("imageId") String imageId, Principal principal) {
        commonImageService.deleteImage(Long.valueOf(imageId), principal);
        return new ResponseEntity<>(new MessageResponse("Image was deleted"), HttpStatus.OK);
    }
}
