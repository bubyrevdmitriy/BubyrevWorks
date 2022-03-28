package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.facade.PostFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.PostService;
import com.example.demo.service.UtilsService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/post")
@CrossOrigin
public class PostController {

    private final PostService postService;
    private final PostFacade postFacade;
    private final ResponseErrorValidation responseErrorValidation;
    private final UtilsService utilsService;

    @Autowired
    public PostController(PostService postService,
                          PostFacade postFacade,
                          ResponseErrorValidation responseErrorValidation,
                          UtilsService utilsService
    ) {
        this.postService = postService;
        this.postFacade = postFacade;
        this.responseErrorValidation = responseErrorValidation;
        this.utilsService = utilsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) String captionSearch,
                                                     @RequestParam(required = false) String page,
                                                     @RequestParam(required = false) String size,
                                                     Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Post> postList;
        if(captionSearch == null) {
            postList = postService.getAllPosts(pageable);
        } else {
            postList = postService.searchPosts(captionSearch, pageable);
        }
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(postFacade.postToPostDTO(post, loginUser));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllPosts(@RequestParam(required = false) String captionSearch) throws IOException {
        Long postsAmount;
        if(captionSearch == null) {
            postsAmount = postService.countAllPosts();
        } else {
            postsAmount = postService.countSearchPosts(captionSearch);
        }
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Object> getSinglePost(@PathVariable("postId") String postId, Principal principal) {
        try {
            User loginUser = utilsService.getUserByPrincipal(principal);
            Post post = postService.getPostById(Long.valueOf(postId));
            PostDTO postExtendedDTO = postFacade.postToPostExtendedDTO(post, loginUser);
            return new ResponseEntity<>(postExtendedDTO, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            String errorIdMessage = "Post with id: " + postId + " not found";
            errorMap.put("postIdError", errorIdMessage);
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPostForUser(@PathVariable("userId") String userId,
                                                           @RequestParam(required = false) String page,
                                                           @RequestParam(required = false) String size,
                                                           Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Post> postList = postService.getAllPostsFromUser(Long.valueOf(userId), pageable);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(postFacade.postToPostDTO(post, loginUser));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/count/{userId}")
    public ResponseEntity<Long> countPostsForUserGroup(@PathVariable("userId") String userId) throws IOException {
        Long imageAmount = postService.countPostsToUser(Long.valueOf(userId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }




    // getAllPostsFromUserGroup(Long userGroupId, Principal principal)
    @GetMapping("/group/{userGroupId}")
    public ResponseEntity<List<PostDTO>> getAllPostForUserGroup(@PathVariable("userGroupId") String userGroupId,
                                                                @RequestParam(required = false) String page,
                                                                @RequestParam(required = false) String size,
                                                                Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Post> postList = postService.getAllPostsFromUserGroup(Long.valueOf(userGroupId), pageable);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            postDTOList.add(postFacade.postToPostDTO(post, loginUser));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/group/count/{userGroupId}")
    public ResponseEntity<Long> countAllPostsForUserGroup(@PathVariable("userGroupId") String userGroupId) throws IOException {
        Long imageAmount = postService.countPostsToUserGroup(Long.valueOf(userGroupId));
        return new ResponseEntity<>(imageAmount, HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO,
                                             BindingResult bindingResult,
                                             Principal principal) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Post post = postService.createPost(postDTO, principal);
        User loginUser = utilsService.getUserByPrincipal(principal);
        PostDTO createdPost = postFacade.postToPostDTO(post, loginUser);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    //createPost(PostDTO postDTO, Long userGroupId, Principal principal)
    @PostMapping("/group/{userGroupId}/create")
    public ResponseEntity<Object> createPostInUserGroup(@Valid @RequestBody PostDTO postDTO,
                                                        @PathVariable("userGroupId") String userGroupId,
                                                        BindingResult bindingResult,
                                                        Principal principal) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Post post = postService.createPost(postDTO, Long.valueOf(userGroupId), principal);
        User loginUser = utilsService.getUserByPrincipal(principal);
        PostDTO createdPost = postFacade.postToPostDTO(post, loginUser);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    /*@PostMapping("/{postId}/like")
    public ResponseEntity<PostDTO> likePost (@PathVariable("postId") String postId,
                                             Principal principal) {
        Post post = postService.likePost(Long.valueOf(postId), principal);
        PostDTO postDTO = postFacade.postToPostDTO(post);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }*/

    @DeleteMapping("/{postId}")
    public ResponseEntity<MessageResponse> deletePost (@PathVariable("postId") String postId, Principal principal) {
        postService.deletePost(Long.valueOf(postId), principal);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }
}
