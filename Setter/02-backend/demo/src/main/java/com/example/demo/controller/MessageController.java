package com.example.demo.controller;


import com.example.demo.dto.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.facade.MessageFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.MessageService;
import com.example.demo.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
//@RequestMapping("/messages")
@CrossOrigin(origins = "*")
//@Controller
public class MessageController {

	private final MessageFacade messageFacade;
	private final MessageService messageService;
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final UtilsService utilsService;

	@Autowired
	public MessageController(MessageFacade messageFacade,
							 MessageService messageService,
							 SimpMessagingTemplate simpMessagingTemplate,
							 UtilsService utilsService
	) {
		this.messageFacade = messageFacade;
		this.messageService = messageService;
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.utilsService = utilsService;
	}

	@MessageMapping("/chat/{to}")//такой же как в Config отсюда приходит сообщение
	public void sendMessage(@DestinationVariable String to,
							@RequestBody MessageDTO messageDTO,
							Principal principal
	) {
		//System.out.println("public void sendMessage");
		//System.out.println(auth_token);
		//System.out.println(messageDTO);
		/*String token = utilsService.getTokenFromString(auth_token);
		System.out.println(token);
		boolean isTokenCorrect = utilsService.validateToken(token);
		System.out.println(isTokenCorrect);*/

		Message message = messageService.createMessage(messageDTO, principal);
		MessageDTO messageSavedDTO = messageFacade.messageToMessageDTO(message);
		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, messageSavedDTO);//такой же как в Config сюда уходят сообщения
	}
	@DeleteMapping(value = "/{messageId}")
	public ResponseEntity<MessageResponse> deleteChannel (@PathVariable("messageId") String messageId, Principal principal) {
		boolean isMessageDeleted = messageService.deleteMessage(Long.valueOf(messageId), principal);
		if (isMessageDeleted) {
			return new ResponseEntity<>(new MessageResponse("Channel was deleted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Channel was deleted, some error occurred"), HttpStatus.BAD_REQUEST);
		}
	}

}
