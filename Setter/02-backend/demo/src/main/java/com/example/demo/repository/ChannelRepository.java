package com.example.demo.repository;

import com.example.demo.entity.Channel;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Optional<Channel> findChannelBySenderUserAndRecipientUser(User senderUser, User recipientUser);

    List<Channel> findAllBySenderUserOrRecipientUser(User senderUser, User recipientUser);

    Channel findChannelById(Long channelId);

    Optional<Channel> findByIdAndSenderUser(Long channelId, User senderUser);

    Optional<Channel> findByIdAndRecipientUser(Long channelId, User recipientUser);

    Optional<Channel> findChannelByIdAndSenderUserOrIdAndRecipientUser(Long channelId1, User senderUser,Long channelId2, User recipientUser);
}
