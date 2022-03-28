package com.example.demo.facade;

import com.example.demo.dto.ChannelDTO;
import com.example.demo.entity.Channel;
import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class ChannelFacade {

    private final UserAuthorFacade userAuthorFacade;
    private final MessageService messageService;

    public ChannelFacade(UserAuthorFacade userAuthorFacade, MessageService messageService) {
        this.userAuthorFacade = userAuthorFacade;
        this.messageService = messageService;
    }

    public ChannelDTO channelToChannelDTO (Channel channel) {
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(channel.getId());
        channelDTO.setSenderUser(userAuthorFacade.userToUserAuthorDTO(channel.getSenderUser()));
        channelDTO.setRecipientUser(userAuthorFacade.userToUserAuthorDTO(channel.getRecipientUser()));

        Message message = messageService.getLastMessageForChannel(channel.getId());
        if (message!=null) {
            channelDTO.setLastMessage(message.getContent());
            channelDTO.setLastDate(message.getCreatedDate());
        } else {
            channelDTO.setLastDate(channel.getCreatedDate());
        }
        return channelDTO;
    }
}
