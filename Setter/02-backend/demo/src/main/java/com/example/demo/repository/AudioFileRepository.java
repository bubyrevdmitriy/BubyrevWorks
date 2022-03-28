package com.example.demo.repository;

import com.example.demo.entity.AudioFile;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {

    long countByUserAndUserGroup(User user, UserGroup userGroup);
    List<AudioFile> findAllByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup, Pageable pageable);

    long countByUserGroup(UserGroup userGroup);
    List<AudioFile> findAllByUserGroupOrderByCreatedDateDesc(UserGroup userGroup, Pageable pageable);

    long countByDescriptionContaining(String description);
    List<AudioFile> findByDescriptionContainingOrderByCreatedDateDesc(String description, Pageable pageable);

    long count();
    List<AudioFile> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
