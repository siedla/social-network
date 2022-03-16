package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.Conversation;
import com.siedla.socialnetwork.model.Message;
import com.siedla.socialnetwork.services.ConversationService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ConversationController {

    private final ConversationService conversationService;


    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;

    }

    @GetMapping(path = "/conversations/{firstUserId}/{secondUserId}")
    public Conversation getConversationByUsersId(@PathVariable Long firstUserId, @PathVariable Long secondUserId){
        return conversationService.findConversation(firstUserId, secondUserId);
    }

    @PostMapping(path = "/conversations/message/{conversationId}")
    public Conversation addMessage(@PathVariable Long conversationId, @RequestBody Message message) {
        return conversationService.addMessage(message, conversationId);
    }

    @MessageMapping("/add/{conversationId}")
    @SendTo("/conversation/message")
    public Message chat(Message message, @DestinationVariable("conversationId") Long conversationId) {
        conversationService.addMessage(message, conversationId);
        return message;
    }
}
