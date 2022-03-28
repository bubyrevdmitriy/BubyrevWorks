package com.example.demo.service;

import com.example.demo.dto.MessageDTO;
import com.example.demo.entity.Channel;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.MessageStatus;
import com.example.demo.exception.ChannelNotFoundException;
import com.example.demo.repository.ChannelRepository;
import com.example.demo.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class MessageService {
    public static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    private final UtilsService utilsService;
    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;

    @Autowired
    public MessageService(UtilsService utilsService,
                          MessageRepository messageRepository,
                          ChannelRepository channelRepository) {
        this.utilsService = utilsService;
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
    }

    public Message createMessage(MessageDTO messageDTO, Principal principal) {
        Channel channel = getChannelById(Long.valueOf(messageDTO.getChannelId()));//channelService.getChannelByIdAndUser(Long.valueOf(messageDTO.getChannelId()), principal);
        User userSender = utilsService.getUserById(messageDTO.getSenderId());//utilsService.getUserByPrincipal(principal);
        User userRecipient = utilsService.getUserById(messageDTO.getRecipientId());

        if(userSender.getId().equals(channel.getRecipientUser().getId()) || userSender.getId().equals(channel.getSenderUser().getId())) {
            Message message = new Message();
            message.setChannel(channel);
            message.setSender(userSender);
            message.setContent(messageDTO.getContent());
            message.setRecipient(userRecipient);
            message.setStatus(MessageStatus.DELIVERED);
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getMessagesForChannel(Long channelId, Principal principal) {
        Channel channel = getChannelByIdAndUser(channelId, principal);
        return messageRepository.findAllByChannelOrderByCreatedDateAsc(channel);
    }

    public Message getLastMessageForChannel(Long channelId) {
        Channel channel = getChannelById(channelId);
        List<Message> lastMessageList = messageRepository.findFirst1ByChannelOrderByCreatedDateDesc(channel);
        if (lastMessageList.size()>0) {
            return lastMessageList.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteMessage(Long messageId, Principal principal) {
        try {
            Message message = getMessageById(messageId);
            User user = utilsService.getUserByPrincipal(principal);
            if (user.getId().equals(message.getSender().getId())) {
                messageRepository.delete(message);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Message getMessageById(Long messageId) {
        return messageRepository.findMessageById(messageId).orElse(null);
    }

    @Transactional
    public void markMessagesAsRead(Long channelId, Principal principal) {
        Channel channel = getChannelByIdAndUser(channelId, principal);
        User userSender = utilsService.getUserByPrincipal(principal);
        List<Message> messagesToRead = messageRepository.findAllByChannelAndSenderNot(channel, userSender);
        if (messagesToRead.size() !=0 ) {
            for (int i = 0; i < messagesToRead.size(); i++) {
                Message messageInLoop = messagesToRead.get(i);
                messageInLoop.setStatus(MessageStatus.RECEIVED);
                messageRepository.save(messageInLoop);
            }
        }
    }

    public Channel getChannelById(Long channelId) {
        Channel channel = channelRepository.findById(channelId).orElse(null);
        return channel;
    }

    public Channel getChannelByIdAndUser(Long channelId, Principal principal) throws ChannelNotFoundException {
        User user = utilsService.getUserByPrincipal(principal);
        return channelRepository.findChannelByIdAndSenderUserOrIdAndRecipientUser(channelId, user, channelId, user)
                .orElseThrow(() -> new ChannelNotFoundException("Cannot find channel with id: " + channelId));
    }
}
