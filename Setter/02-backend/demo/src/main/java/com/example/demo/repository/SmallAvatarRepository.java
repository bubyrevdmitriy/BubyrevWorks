package com.example.demo.repository;

import com.example.demo.entity.SmallAvatar;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmallAvatarRepository  extends JpaRepository<SmallAvatar, Long> {

    Optional<SmallAvatar> findByUser(User user);
}
