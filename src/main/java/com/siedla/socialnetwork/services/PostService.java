package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.repositories.PostRepository;
import com.siedla.socialnetwork.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts(){
        List<Post> postList = new LinkedList<>();
        postRepository.findAll().iterator().forEachRemaining(postList::add);
        postList.sort(Comparator.comparing(Post::getId).reversed());
        return postList;
    }

    public List<Post> getPostsBetweenId(Long from, Long to){
        List<Post> postList = new LinkedList<>();
        postRepository.findAllByIdBetween(from, to).iterator().forEachRemaining(postList::add);

        postList.sort(Comparator.comparing(Post::getId).reversed());
        return postList;
    }

    public List<Post> getPostsByUserId(Long id){
        List<Post> postList = new LinkedList<>();
        Optional<List<Post>> listOptional = postRepository.findByUserId(id);
        listOptional.ifPresent(postList::addAll);
        postList.sort(Comparator.comparing(Post::getPostDate).reversed());
        return postList;
    }

    public Post addPost(Post post, Long userId) {

        post.getUser().setId(userId);
        post.setLikes(0L);
        post.setPostDate(LocalDateTime.now());
        Optional<User> user = userRepository.findById(post.getUser().getId());
        user.ifPresent(value -> value.addPost(post));
        return postRepository.save(post);
    }

    public Post getPostById(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post updatePost(Post post, Long id) {
        post.setId(id);
        return postRepository.save(post);
    }

    public Post likePost(Post post, Long postId, Long userId){
        Post updatePost = postRepository.findById(postId).get();
        updatePost.setLikes(updatePost.getLikes()+1);
        User user = userRepository.findById(userId).get();
        post.getLikedBy().add(user);
        user.getLikedPosts().add(updatePost);
        userRepository.save(user);
        return postRepository.save(updatePost);
    }

    public Post dislikePost(Post post, Long postId, Long userId){
        Post updatePost = postRepository.findById(postId).get();
        updatePost.setLikes(updatePost.getLikes()-1);
        User user = userRepository.findById(userId).get();
        post.getLikedBy().remove(user);
        user.getLikedPosts().remove(updatePost);
        userRepository.save(user);
        return postRepository.save(updatePost);
    }


}
