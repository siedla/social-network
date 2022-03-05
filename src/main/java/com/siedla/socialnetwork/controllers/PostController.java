package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.services.PostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getUsersPosts(@PathVariable Long id) {
        return postService.getPostsByUserId(id);
    }
}
