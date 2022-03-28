package com.example.demo.repository;

import com.example.demo.entity.UserGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    long count();
    List<UserGroup> findAllByOrderByCreatedDateDesc(Pageable pageable);

    long countByNameContaining(String name);
    List<UserGroup> findByNameContainingOrderByCreatedDateDesc(String name, Pageable pageable);
}
