package com.example.demo.controller;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.ImageModel;
import com.example.demo.facade.ImageFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageUploadController {

    private final ImageService imageService;
    private final ImageFacade imageFacade;

    @Autowired
    public ImageUploadController(ImageService imageService,
                                 ImageFacade imageFacade
    ) {
        this.imageService = imageService;
        this.imageFacade = imageFacade;
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        ImageModel userImage = imageService.uploadImageToUser(file, principal);
        return new ResponseEntity<>(imageFacade.imageToImageDTO(userImage), HttpStatus.OK);
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<ImageDTO> uploadImageToPost(@PathVariable("postId") String postId, @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        ImageModel postImage = imageService.uploadImageToPost(file, Long.parseLong(postId), principal);
        return new ResponseEntity<>(imageFacade.imageToImageDTO(postImage), HttpStatus.OK);
    }

    /*@GetMapping("/user/{userId}")
    public ResponseEntity<ImageDTO> getImageForUser(@PathVariable("userId") String userId) throws IOException {
        ImageModel userImage = imageService.getImageToUser(userId);
        return new ResponseEntity<>(imageFacade.imageToImageDTO(userImage), HttpStatus.OK);
    }*/

    /*@GetMapping("/post/{postId}")
    public ResponseEntity<ImageDTO> getImageToPost(@PathVariable("postId") String postId) throws IOException {
        ImageModel postImage = imageService.getImageToPost(Long.parseLong(postId));
        return new ResponseEntity<>(imageFacade.imageToImageDTO(postImage), HttpStatus.OK);
    }*/
}
