package com.example.demo.facade;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    private final UserAuthorFacade userAuthorFacade;
    private final UserLikeService userLikeService;
    private final CommentService commentService;

    @Autowired
    public CommentFacade(UserAuthorFacade userAuthorFacade, UserLikeService userLikeService, CommentService commentService) {
        this.userAuthorFacade = userAuthorFacade;
        this.userLikeService = userLikeService;
        this.commentService = commentService;
    }

    public CommentDTO commentToCommentDTO (Comment comment, User loginUser) {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        comment.setLikes(comment.getLikes());
        commentDTO.setUserAuthor(userAuthorFacade.userToUserAuthorDTO(comment.getUser()));

        if (comment.getPost() != null) {
            commentDTO.setPostId(comment.getPost().getId());
        }
        if (comment.getCommonImage() != null) {
            commentDTO.setCommonImageId(comment.getCommonImage().getId());
        }

        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setLikedByLoginUser(userLikeService.isUserLikeComment(loginUser, comment));
        commentDTO.setLoginUserAuthor(commentService.isUserAuthor(comment, loginUser));
        return commentDTO;
    }
}
