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
import java.util.Map;

@RestController
@RequestMapping("api/comment")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;
    private final CommentFacade commentFacade;
    private final ResponseErrorValidation responseErrorValidation;
    private final UtilsService utilsService;

    @Autowired
    public CommentController(CommentService commentService,
                             CommentFacade commentFacade,
                             ResponseErrorValidation responseErrorValidation,
                             UtilsService utilsService
    ) {
        this.commentService = commentService;
        this.commentFacade = commentFacade;
        this.responseErrorValidation = responseErrorValidation;
        this.utilsService = utilsService;
    }

    @GetMapping("/posts/{postId}/all")
    public ResponseEntity<List<CommentDTO>> getAllCommentToPost(@PathVariable("postId") String postId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Comment> commentList = commentService.getAllCommentsByPost(Long.parseLong(postId));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOList.add(commentFacade.commentToCommentDTO(comment, loginUser));
        }
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping("/commonImages/{commonImageId}/all")
    public ResponseEntity<List<CommentDTO>> getAllCommentToCommonImage(@PathVariable("commonImageId") String commonImageId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Comment> commentList = commentService.getAllCommentsByCommonImage(Long.parseLong(commonImageId));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOList.add(commentFacade.commentToCommentDTO(comment, loginUser));
        }
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping("/videoFiles/{videoFileId}/all")
    public ResponseEntity<List<CommentDTO>> getAllCommentToVideoFile(@PathVariable("videoFileId") String videoFileId, Principal principal) {
        User loginUser = utilsService.getUserByPrincipal(principal);
        List<Comment> commentList = commentService.getAllCommentsByVideoFile(Long.parseLong(videoFileId));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOList.add(commentFacade.commentToCommentDTO(comment, loginUser));
        }
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }



    @PostMapping("/posts/{postId}/create")
    public ResponseEntity<Object> createCommentToPost(@Valid @RequestBody CommentDTO commentDTO,
                                                  @PathVariable("postId") String postId,
                                                  BindingResult bindingResult,
                                                  Principal principal
    ) {
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);// check errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);//return errors
        }
        Comment comment = commentService.saveCommentToPost(Long.parseLong(postId), commentDTO, principal);
        User loginUser = utilsService.getUserByPrincipal(principal);
        CommentDTO createdComment = commentFacade.commentToCommentDTO(comment, loginUser);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @PostMapping("/commonImages/{commonImageId}/create")
    public ResponseEntity<Object> createCommentToCommonImage(@Valid @RequestBody CommentDTO commentDTO,
                                                      @PathVariable("commonImageId") String commonImageId,
                                                      BindingResult bindingResult,
                                                      Principal principal
    ) {
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);// check errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);//return errors
        }
        Comment comment = commentService.saveCommentToCommonImage(Long.parseLong(commonImageId), commentDTO, principal);
        User loginUser = utilsService.getUserByPrincipal(principal);
        CommentDTO createdComment = commentFacade.commentToCommentDTO(comment, loginUser);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @PostMapping("/videoFiles/{videoFileId}/create")
    public ResponseEntity<Object> createCommentToVideoFile(@Valid @RequestBody CommentDTO commentDTO,
                                                             @PathVariable("videoFileId") String videoFileId,
                                                             BindingResult bindingResult,
                                                             Principal principal
    ) {
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);// check errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);//return errors
        }
        Comment comment = commentService.saveCommentToVideoFile(Long.parseLong(videoFileId), commentDTO, principal);
        User loginUser = utilsService.getUserByPrincipal(principal);
        CommentDTO createdComment = commentFacade.commentToCommentDTO(comment, loginUser);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") String commentId, Principal principal) {
        commentService.deleteComment(Long.valueOf(commentId), principal);
        return new ResponseEntity<>(new MessageResponse("Comment was deleted"), HttpStatus.OK);
    }
}
