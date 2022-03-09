package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/posts/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @GetMapping(path = "/posts/{from}/{to}")
    public List<Post> getPostsBetweenId(@PathVariable Long from, @PathVariable Long to) {
        return postService.getPostsBetweenId(from, to);
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getUsersPosts(@PathVariable Long id) {
        return postService.getPostsByUserId(id);
    }

    @PostMapping(path = "/posts/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Post addPost(@RequestBody Post post, @PathVariable Long userId) {
        return postService.addPost(post, userId);
    }

    @PutMapping(path = "/posts/{postId}")
    public Post updatePost(@RequestBody Post post, @PathVariable Long postId){
        return postService.updatePost(post, postId);
    }
}
