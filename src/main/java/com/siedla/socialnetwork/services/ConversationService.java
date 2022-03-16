package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.model.Conversation;
import com.siedla.socialnetwork.model.Message;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.repositories.ConversationRepository;
import com.siedla.socialnetwork.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public Conversation findConversation(Long firstUserId, Long secondUserId) {
        Optional<User> firstUser = userRepository.findById(firstUserId);
        Optional<User> secondUser = userRepository.findById(secondUserId);
        return conversationRepository.findConversationsByUsersIsContainingAndUsersIsContaining(firstUser.get(), secondUser.get());
    }

    public Conversation addMessage(Message message, Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).get();
        conversation.getMessages().add(message);
        message.setConversation(conversation);
        return conversationRepository.save(conversation);
    }
}
