package com.siedla.socialnetwork.bootstrap;


import com.siedla.socialnetwork.model.Conversation;
import com.siedla.socialnetwork.model.Message;
import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.repositories.ConversationRepository;
import com.siedla.socialnetwork.repositories.MessageRepository;
import com.siedla.socialnetwork.repositories.PostRepository;
import com.siedla.socialnetwork.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class PostBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public PostBootstrap(UserRepository userRepository, PostRepository postRepository, MessageRepository messageRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        postRepository.saveAll(getPosts());
        addMessages();
        userRepository.save(new User("Jan", "Nowak", "email@email.com", "$2a$10$ixlPY3AAd4ty1l6E2IsQ9OFZi2ba9ZQE0bP7RFcGIWNhyFrrT3YUi"));
    }

    private List<Post> getPosts() {
        List<Post> posts = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(1L);
        Optional<User> user2Optional = userRepository.findById(2L);
        if(userOptional.isEmpty() || user2Optional.isEmpty()){
            throw new RuntimeException("Expected User Not Found");
        }

        User user1 = userOptional.get();
        User user2 = user2Optional.get();
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        userRepository.save(user1);
        Post user1Post = new Post();
        user1Post.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        user1Post.setPostDate(LocalDateTime.of(2022, Month.FEBRUARY, 28, 11, 23, 44));
        user1Post.setLikes(11L);
        user1.addPost(user1Post);

        posts.add(user1Post);

        Post user2Post = new Post();
        user2Post.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        user2Post.setPostDate(LocalDateTime.of(2022, Month.MARCH, 2, 20, 20, 1));
        user2Post.setLikes(18L);
        user2.addPost(user2Post);

        posts.add(user2Post);

        return posts;
    }

    private void addMessages(){
        Optional<User> userOptional = userRepository.findById(1L);
        Optional<User> user2Optional = userRepository.findById(2L);
        if(userOptional.isEmpty() || user2Optional.isEmpty()){
            throw new RuntimeException("Expected User Not Found");
        }

        User user1 = userOptional.get();
        User user2 = user2Optional.get();
        Conversation conversation = new Conversation();
        conversation.getUsers().add(user1);
        conversation.getUsers().add(user2);
        user1.getConversations().add(conversation);
        user2.getConversations().add(conversation);

        Message message = new Message();
        message.setFromId(user1.getId());
        message.setText("Test czy dziala");
        message.setPostDate(LocalDateTime.now());
        message.setConversation(conversation);
        conversation.getMessages().add(message);
        conversationRepository.save(conversation);
        userRepository.save(user1);
        userRepository.save(user2);


    }
}
