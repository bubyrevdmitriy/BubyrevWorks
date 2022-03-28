package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFriendInviteRepository extends JpaRepository<UserFriendInvite, Long> {

    List<UserFriendInvite> findAllBySenderUser(User senderUser);

    List<UserFriendInvite> findAllByRecipientUser(User recipientUser);

    List<UserFriendInvite> findAllBySenderUserOrRecipientUser(User senderUser, User recipientUser);

    Optional<UserFriendInvite> findBySenderUserAndRecipientUser(User senderUser, User recipientUser);
}
