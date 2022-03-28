package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.UserGroupPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupPairRepository  extends JpaRepository<UserGroupPair, Long> {

    List<UserGroupPair> findAllByUser(User user);

    List<UserGroupPair> findAllByUserGroup(UserGroup userGroup);

    Optional<UserGroupPair> findByUserAndUserGroup(User user, UserGroup userGroup);
}
