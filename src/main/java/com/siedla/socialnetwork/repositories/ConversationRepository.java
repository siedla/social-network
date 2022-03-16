package com.siedla.socialnetwork.repositories;

import com.siedla.socialnetwork.model.Conversation;
import com.siedla.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    Conversation findConversationsByUsersIsContainingAndUsersIsContaining(User user, User user2);
}
