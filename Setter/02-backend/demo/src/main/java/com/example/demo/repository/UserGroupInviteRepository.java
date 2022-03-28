package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.UserGroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupInviteRepository extends JpaRepository<UserGroupInvite, Long> {

    List<UserGroupInvite> findAllBySenderUser(User senderUser);

    List<UserGroupInvite> findAllByRecipientUser(User recipientUser);

    List<UserGroupInvite> findAllByUserGroup(UserGroup userGroup);

    List<UserGroupInvite> findAllByRecipientUserAndUserGroup(User senderUser, UserGroup userGroup);

    Optional<UserGroupInvite> findBySenderUserAndRecipientUserAndUserGroup(User senderUser, User recipientUser, UserGroup userGroup);

    Optional<UserGroupInvite> findByRecipientUserAndUserGroup(User senderUser, UserGroup userGroup);
}
