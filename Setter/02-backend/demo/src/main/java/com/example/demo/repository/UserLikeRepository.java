package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikeRepository  extends JpaRepository<UserLike, Long> {

    List<UserLike> findAllByAudioFile(AudioFile audioFile);
    Optional<UserLike> findByUserAndAudioFile(User user, AudioFile audioFile);

    List<UserLike> findAllByComment(Comment comment);
    Optional<UserLike> findByUserAndComment(User user, Comment comment);

    List<UserLike> findAllByCommonImage(CommonImage commonImage);
    Optional<UserLike> findByUserAndCommonImage(User user, CommonImage commonImage);

    List<UserLike> findAllByPost(Post post);
    Optional<UserLike> findByUserAndPost(User user, Post post);

    List<UserLike> findAllByVideoFile(VideoFile videoFile);
    Optional<UserLike> findByUserAndVideoFile(User user, VideoFile videoFile);
}
