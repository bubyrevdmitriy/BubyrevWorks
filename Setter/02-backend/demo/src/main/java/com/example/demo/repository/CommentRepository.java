package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommonImage;
import com.example.demo.entity.Post;
import com.example.demo.entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
    List<Comment> findAllByCommonImage(CommonImage commonImage);
    List<Comment> findAllByVideoFile(VideoFile videoFile);
}
