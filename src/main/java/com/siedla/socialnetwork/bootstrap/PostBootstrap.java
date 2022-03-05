package com.siedla.socialnetwork.bootstrap;


import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.repositories.PostRepository;
import com.siedla.socialnetwork.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class PostBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostBootstrap(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        postRepository.saveAll(getPosts());
        userRepository.save(new User("Jan", "Kowal", "aasdas@email.com"));
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

        Post user1Post = new Post();
        user1Post.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        user1Post.setPostDate(LocalDate.of(2022, Month.FEBRUARY, 28));
        user1Post.setPostTime(LocalTime.of(10,43,12));
        user1Post.setLikes(11L);
        user1.addPost(user1Post);

        posts.add(user1Post);

        Post user2Post = new Post();
        user2Post.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        user2Post.setPostDate(LocalDate.of(2022, Month.MARCH, 2));
        user2Post.setPostTime(LocalTime.of(21,43,13));
        user2Post.setLikes(18L);
        user2.addPost(user2Post);

        posts.add(user2Post);

        return posts;

    }
}
