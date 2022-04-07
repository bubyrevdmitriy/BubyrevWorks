package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.facade.CommentFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.CommentService;
import com.example.demo.service.UtilsService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comment")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;
    private final CommentFacade commentFacade;
    private final UtilsService utilsService;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public CommentController(CommentService commentService,
                             CommentFacade commentFacade,
                             UtilsService utilsService,
                             ResponseErrorValidation responseErrorValidation
    ) {
        this.commentService = commentService;
        this.commentFacade = commentFacade;
        this.utilsService = utilsService;
        this.responseErrorValidation = responseErrorValidation;
    }

    @PostMapping("/{postId}/create")
    public ResponseEntity<Object> createComponent(@Valid @RequestBody CommentDTO commentDTO,
                                                  @PathVariable("postId") String postId,
                                                  BindingResult bindingResult,
                                                  Principal principal
    ) {
        // check errors
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User loginUser = utilsService.getUserByPrincipal(principal);
        Comment comment = commentService.saveComment(Long.parseLong(postId), commentDTO, loginUser);
        CommentDTO createdComment = commentFacade.commentToCommentDTO(comment, loginUser);

        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @GetMapping("/{postId}/all")
    public ResponseEntity<List<CommentDTO>> getAllCommentToPost(@PathVariable("postId") String postId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Comment> commentList = commentService.getAllCommentsByPost(Long.parseLong(postId));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOList.add(commentFacade.commentToCommentDTO(comment, loginUser));
        }
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        commentService.deleteComment(Long.valueOf(commentId), loginUser);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }


}
