package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.videoServices.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CommentService {

    public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final UtilsService utilsService;
    private final PostService postService;
    private final CommonImageService commonImageService;
    private final VideoService videoService;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          UtilsService utilsService,
                          PostService postService,
                          CommonImageService commonImageService,
                          VideoService videoService) {
        this.commentRepository = commentRepository;
        this.utilsService = utilsService;
        this.postService = postService;
        this.commonImageService = commonImageService;
        this.videoService = videoService;
    }

    public Comment saveCommentToPost (Long postId, CommentDTO commentDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setLikes(0);
        comment.setUser(user);
        comment.setPost(post);
        LOG.info("Saving comment for Post: {}", post.getId());
        return commentRepository.save(comment);
    }

    public Comment saveCommentToCommonImage (Long commonImageId, CommentDTO commentDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        CommonImage commonImage = commonImageService.getCommonImageById(commonImageId);
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setLikes(0);
        comment.setUser(user);
        comment.setCommonImage(commonImage);
        LOG.info("Saving comment for CommonImage: {}", commonImage.getId());
        return commentRepository.save(comment);
    }

    public Comment saveCommentToVideoFile (Long videoFileId, CommentDTO commentDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        VideoFile videoFile = videoService.getVideoFileById(videoFileId);
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setLikes(0);
        comment.setUser(user);
        comment.setVideoFile(videoFile);
        LOG.info("Saving comment for videoFile: {}", videoFile.getId());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsByPost(Long postId) {
        Post post = postService.getPostById(postId);
        return commentRepository.findAllByPost(post);
    }

    public List<Comment> getAllCommentsByCommonImage(Long commonImageId) {
        CommonImage commonImage = commonImageService.getCommonImageById(commonImageId);
        return commentRepository.findAllByCommonImage(commonImage);
    }

    public List<Comment> getAllCommentsByVideoFile(Long videoFileId) {
        VideoFile videoFile = videoService.getVideoFileById(videoFileId);
        return commentRepository.findAllByVideoFile(videoFile);
    }

    public void deleteComment(Long commentId, Principal principal) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Cannot find comment with id: " + commentId));
        User user = utilsService.getUserByPrincipal(principal);

        if (user.getId().equals(comment.getUser().getId())) {
            commentRepository.delete(comment);
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Cannot find comment with id: " + commentId));
    }

    public boolean isUserAuthor(Comment comment, User user) {
        if (user.getId().equals(comment.getUser().getId())) {
            return true;
        }
        return false;
    }
}
