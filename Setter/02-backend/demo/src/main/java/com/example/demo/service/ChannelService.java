package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.ChannelNotFoundException;
import com.example.demo.repository.ChannelRepository;
import com.example.demo.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class ChannelService {
    public static final Logger LOG = LoggerFactory.getLogger(ChannelService.class);

    private final ChannelRepository channelRepository;
    private final UtilsService utilsService;
    private final MessageService messageService;
    private final MessageRepository messageRepository;

    public ChannelService(ChannelRepository channelRepository,
                          UtilsService utilsService,
                          MessageService messageService,
                          MessageRepository messageRepository
    ) {
        this.channelRepository = channelRepository;
        this.utilsService = utilsService;
        this.messageService = messageService;
        this.messageRepository = messageRepository;
    }

    public Channel createChannel(Long recipientUserId, Principal principal) {
        User senderUser = utilsService.getUserByPrincipal(principal);
        User recipientUser = utilsService.getUserById(recipientUserId);
        Channel existChannel = getChannelByUsers(senderUser, recipientUser);
        if (existChannel == null) {
            Channel channel = new Channel();
            channel.setSenderUser(senderUser);
            channel.setRecipientUser(recipientUser);
            LOG.info("Saving Channel for Users: {} {}", senderUser.getId(), recipientUser.getId());
            return channelRepository.save(channel);
        } else {
            return existChannel;
        }
    }

    @Transactional
    public boolean deleteChannel(Long channelId, Principal principal)  throws ChannelNotFoundException {
        try {
            Channel channel = getChannelByIdAndUser(channelId, principal);
            List<Message> messageList= messageService.getMessagesForChannel(Long.valueOf(channelId), principal);

            for (Message message : messageList) {
                messageRepository.delete(message);
            }

            channelRepository.delete(channel);
            return true;
        } catch (Exception e) {}
        return false;
    }

    public List<Channel> getChannelsForUser(Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        return channelRepository.findAllBySenderUserOrRecipientUser(user, user);
    }

    public Channel getChannelByUsers(User user1, User user2) {
        return channelRepository.findChannelBySenderUserAndRecipientUser(user1, user2)
                .orElse(channelRepository.findChannelBySenderUserAndRecipientUser(user2, user1)
                        .orElse(null));
    }

    public Channel getChannelByIdAndUser(Long channelId, Principal principal) throws ChannelNotFoundException {
        User user = utilsService.getUserByPrincipal(principal);
        return channelRepository.findChannelByIdAndSenderUserOrIdAndRecipientUser(channelId, user, channelId, user)
                .orElseThrow(() -> new ChannelNotFoundException("Cannot find channel with id: " + channelId));
    }

    public boolean isMyChannel(Long channelId, Principal principal) {
        try {
            Channel channel = getChannelByIdAndUser(channelId, principal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Channel getChannelById(Long channelId) {
        Channel channel = channelRepository.findById(channelId).orElse(null);
        return channel;
    }
}
