package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.VideoFile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {

    long countByUserAndUserGroup(User user, UserGroup userGroup);
    List<VideoFile> findAllByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup, Pageable pageable);

    long countByUserGroup(UserGroup userGroup);
    List<VideoFile> findAllByUserGroupOrderByCreatedDateDesc(UserGroup userGroup, Pageable pageable);

    long countByDescriptionContaining(String description);
    List<VideoFile> findByDescriptionContainingOrderByCreatedDateDesc(String description, Pageable pageable);

    long count();
    List<VideoFile> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
