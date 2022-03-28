package com.example.demo.repository;

import com.example.demo.entity.CommonImage;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonImageRepository  extends JpaRepository<CommonImage, Long> {

    List<CommonImage> findByUserOrderByCreatedDateDesc(User user);

    long countByUserAndUserGroup(User user, UserGroup userGroup);
    List<CommonImage> findByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup);
    List<CommonImage> findByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup, Pageable pageable);

    long countByUserGroup(UserGroup userGroup);
    List<CommonImage> findByUserGroupOrderByCreatedDateDesc(UserGroup userGroup);
    List<CommonImage> findByUserGroupOrderByCreatedDateDesc(UserGroup userGroup, Pageable pageable);

    List<CommonImage> findByPost(Post post);
    Optional<CommonImage> findById(Long id);
}
