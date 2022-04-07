package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);


    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UtilsService utilsService;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UtilsService utilsService
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.utilsService = utilsService;
    }

    public Comment saveComment (Long postId,
                                CommentDTO commentDTO,
                                User user
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Cannot find post with id: " + postId));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setMessage(commentDTO.getMessage());

        LOG.info("Saving comment for Post: {}", post.getId());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Cannot find post with id: " + postId));
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments;
    }

    public void deleteComment(Long commentId, User user) {
        Optional<Comment> comment = commentRepository.findByIdAndUser(commentId, user);
        comment.ifPresent(commentRepository::delete);
    }

    public boolean isUserAuthor(Comment comment, User user) {
        if (user.getId().equals(comment.getUser().getId())) {
            return true;
        }
        return false;
    }
}
