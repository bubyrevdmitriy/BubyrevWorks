package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    long countByUserGroup(UserGroup userGroup);
    List<Post> findByUserGroupOrderByCreatedDateDesc (UserGroup userGroup);
    List<Post> findByUserGroupOrderByCreatedDateDesc(UserGroup userGroup, Pageable pageable);

    long count();
    List<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    long countByUserAndUserGroup(User user, UserGroup userGroup);
    List<Post> findByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup);
    List<Post> findByUserAndUserGroupOrderByCreatedDateDesc(User user, UserGroup userGroup, Pageable pageable);

    long countByCaptionContaining(String caption);
    List<Post> findByCaptionContainingOrderByCreatedDateDesc(String caption, Pageable pageable);
}
