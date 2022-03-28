package com.example.demo.repository;

import com.example.demo.entity.SmallAvatarGroup;
import com.example.demo.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmallAvatarGroupRepository extends JpaRepository<SmallAvatarGroup, Long> {

    Optional<SmallAvatarGroup> findByUserGroup(UserGroup userGroup);
}
