package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.facade.PostFacade;
import com.example.demo.service.FeedService;
import com.example.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/feed")
@CrossOrigin
public class FeedController {

    private final FeedService feedService;
    private final PostFacade postFacade;
    private final UtilsService utilsService;

    @Autowired
    public FeedController(FeedService feedService, PostFacade postFacade, UtilsService utilsService) {
        this.feedService = feedService;
        this.postFacade = postFacade;
        this.utilsService = utilsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllFeed(@RequestParam(required = false) String page,
                                                    @RequestParam(required = false) String size,
                                                    Principal principal
    ) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Post> postList = feedService.getAllFeedForUserPagination(principal, pageable);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            if (post != null) {
                postDTOList.add(postFacade.postToPostDTO(post, loginUser));
            }
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllPosts(Principal principal) throws IOException {
        Long postsAmount = feedService.countAllFeedForUser(principal);
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }
}
