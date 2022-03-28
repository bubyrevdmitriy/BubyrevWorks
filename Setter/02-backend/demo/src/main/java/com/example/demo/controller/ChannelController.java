package com.example.demo.controller;

import com.example.demo.dto.ChannelDTO;
import com.example.demo.dto.MessageDTO;
import com.example.demo.entity.Channel;
import com.example.demo.entity.Message;
import com.example.demo.facade.ChannelFacade;
import com.example.demo.facade.MessageFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.ChannelService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/channel")
@CrossOrigin
public class ChannelController {

    private final ChannelFacade channelFacade;
    private final MessageFacade messageFacade;
    private final MessageService messageService;
    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelFacade channelFacade,
                             MessageFacade messageFacade,
                             MessageService messageService,
                             ChannelService channelService
    ) {
        this.channelFacade = channelFacade;
        this.messageFacade = messageFacade;
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @PostMapping("/create/{recipientUserId}")
    public ResponseEntity<Object> createChannel(@PathVariable("recipientUserId") String recipientUserId, Principal principal) {

        Channel channel = channelService.createChannel(Long.valueOf(recipientUserId), principal);
        return new ResponseEntity<>(channel.getId(), HttpStatus.OK);
    }

    @GetMapping("/myChannels")
    public ResponseEntity<List<ChannelDTO>> getAllChannelsForUser(Principal principal) {

        List<ChannelDTO> channelDTOList = channelService.getChannelsForUser(principal)
                .stream()
                .map(channelFacade::channelToChannelDTO)
                .collect(Collectors.toList());

        List<ChannelDTO> channelDTOSortedList = new ArrayList<>(Arrays.asList(bubbleSortChannel(channelDTOList)));

        return new ResponseEntity<>(channelDTOSortedList, HttpStatus.OK);
    }


    @GetMapping(value = "/shortInfo/{channelId}")
    public ResponseEntity<ChannelDTO> getChannelShortInfo(@PathVariable("channelId") String channelId, Principal principal) {
        boolean isMyChannel = channelService.isMyChannel(Long.valueOf(channelId), principal);
        if (isMyChannel) {
            Channel channel = channelService.getChannelByIdAndUser(Long.valueOf(channelId), principal);
            ChannelDTO channelDTO = channelFacade.channelToChannelDTO(channel);
            return new ResponseEntity<>(channelDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{channelId}")
    public ResponseEntity<List<MessageDTO>> findMessagesForChannel(@PathVariable("channelId") String channelId, Principal principal) {
        boolean isMyChannel = channelService.isMyChannel(Long.valueOf(channelId), principal);
        if (isMyChannel) {
            List<MessageDTO> messageDTOList = messageService.getMessagesForChannel(Long.valueOf(channelId), principal)
                    .stream()
                    .map(messageFacade::messageToMessageDTO)
                    .collect(Collectors.toList());

            messageService.markMessagesAsRead(Long.valueOf(channelId), principal);

            return new ResponseEntity<>(messageDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping(value = "/{channelId}")
    public ResponseEntity<MessageResponse> deleteChannel (@PathVariable("channelId") String channelId, Principal principal) {
        boolean isMyChannel = channelService.isMyChannel(Long.valueOf(channelId), principal);
        if (isMyChannel) {
            boolean isDeleteSuccessfully = channelService.deleteChannel(Long.valueOf(channelId), principal);
            if (isDeleteSuccessfully) {
                return new ResponseEntity<>(new MessageResponse("Channel was deleted"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("Channel was deleted, some error occurred"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Channel was deleted, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

    public static ChannelDTO[] bubbleSortChannel(List<ChannelDTO> channelDTOList) {
      ChannelDTO[] arr = new ChannelDTO[channelDTOList.size()];
      channelDTOList.toArray(arr);
      //Внешний цикл каждый раз сокращает фрагмент массива, так как внутренний цикл каждый раз ставит в конец фрагмента максимальный элемент

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
            //Сравниваем элементы попарно, если они имеют неправильный порядок, то меняем местами
                if (arr[j].getLastDate().isBefore(arr[j + 1].getLastDate())) {
                    ChannelDTO tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }
}
