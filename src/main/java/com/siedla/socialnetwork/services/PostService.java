package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(){
        List<Post> postList = new LinkedList<>();
        postRepository.findAll().iterator().forEachRemaining(postList::add);
        return postList;
    }

    public List<Post> getPostsByUserId(Long id){
        List<Post> postList = new LinkedList<>();
        Optional<List<Post>> listOptional = postRepository.findByUserId(id);
        listOptional.ifPresent(postList::addAll);
        return postList;
    }

}
