package com.example.demo.facade;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    private final CommentService commentService;
    private final UserNameFacade userNameFacade;

    @Autowired
    public CommentFacade(CommentService commentService,
                         UserNameFacade userNameFacade
    ) {
        this.commentService = commentService;
        this.userNameFacade = userNameFacade;
    }

    public CommentDTO commentToCommentDTO (Comment comment,  User loginUser) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setLoginUserAuthor(commentService.isUserAuthor(comment, loginUser));
        commentDTO.setAuthor(userNameFacade.userToUserNameDTO(comment.getUser()));
        return commentDTO;
    }

}
