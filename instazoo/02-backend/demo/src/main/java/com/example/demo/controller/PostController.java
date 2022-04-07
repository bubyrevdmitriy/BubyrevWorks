package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.facade.PostFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.PostService;
import com.example.demo.service.UtilsService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/post")
@CrossOrigin
public class PostController {

    private final PostService postService;
    private final UtilsService utilsService;
    private final PostFacade postFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public PostController(PostService postService,
                          UtilsService utilsService,
                          PostFacade postFacade,
                          ResponseErrorValidation responseErrorValidation
    ) {
        this.postService = postService;
        this.utilsService = utilsService;
        this.postFacade = postFacade;
        this.responseErrorValidation = responseErrorValidation;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO,
                                             BindingResult bindingResult,
                                             Principal principal
    ) throws IOException {
        // check errors
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User loginUser = utilsService.getUserByPrincipal(principal);
        Post post = postService.createPost(postDTO, loginUser);
        PostDTO createdPost = postFacade.postToPostDTO(post, loginUser);

        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(Principal principal) throws IOException {
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Post> postList = postService.getAllPosts();
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(postFacade.postToPostDTO(post, loginUser));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPostForUser(@PathVariable("userId") String userId, Principal principal) throws IOException {
        User loginUser = utilsService.getUserByPrincipal(principal);
        User userPage = utilsService.getUserById(Long.valueOf(userId));
        List<Post> postList = postService.getAllPostsFromUser(userPage);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(postFacade.postToPostDTO(post, loginUser));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<MessageResponse> deletePost (@PathVariable("postId") String postId, Principal principal) {
        postService.deletePost(Long.valueOf(postId), principal);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }
}
