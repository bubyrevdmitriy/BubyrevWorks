package com.example.demo.repository;


import com.example.demo.entity.Channel;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Page<Message> findAllByChannelOrderByCreatedDateDesc(Channel channel, Pageable pageable);
	List<Message> findAllByChannelOrderByCreatedDateDesc(Channel channel);

	Page<Message> findAllByChannelOrderByCreatedDateAsc(Channel channel, Pageable pageable);
	List<Message> findAllByChannelOrderByCreatedDateAsc(Channel channel);


	List<Message> findFirst1ByChannelOrderByCreatedDateDesc(Channel channel);

	List<Message> findAllByChannelAndSenderNot(Channel channel, User sender);

	@Modifying
	@Query(value = "update message set readDate = now() "
			+ " where channel = ?1 and sender = ?2 and readDate is null", nativeQuery = true)
	void sendReadReceipt(String channel, String username);

	Optional<Message> findMessageById(Long messageId);
}
