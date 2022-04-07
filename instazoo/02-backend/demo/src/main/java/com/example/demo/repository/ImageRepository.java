package com.example.demo.repository;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    List<ImageModel> findAllByUserOrderByCreatedDateDesc (User user);

    List<ImageModel> findByPost(Post post);

    Optional<ImageModel> findById(Long id);

    Optional<ImageModel> findByUserAndPost(User user, Post post);
}
