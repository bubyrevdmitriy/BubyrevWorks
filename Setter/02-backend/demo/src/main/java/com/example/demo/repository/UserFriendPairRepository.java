package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFriendPairRepository extends JpaRepository<UserFriendPair, Long> {

    List<UserFriendPair> findAllBySenderUserOrRecipientUser(User senderUser, User recipientUser);

    Optional<UserFriendPair> findBySenderUserAndRecipientUser(User senderUser, User recipientUser);
}
