package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.Post;
import com.siedla.socialnetwork.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping(path = "/posts/{from}/{to}")
    public ResponseEntity<List<Post>> getPostsBetweenId(@PathVariable Long from, @PathVariable Long to) {
        return ResponseEntity.ok(postService.getPostsBetweenId(from, to));
    }

    @GetMapping(path = "/posts/user/{id}")
    public ResponseEntity<List<Post>> getUsersPosts(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }

    @PostMapping(path = "/posts/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Post> addPost(@RequestBody Post post, @PathVariable Long userId) {
        return ResponseEntity.ok(postService.addPost(post, userId));
    }

    @PutMapping(path = "/posts/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long postId){
        return ResponseEntity.ok(postService.updatePost(post, postId));
    }

    @PutMapping(path = "/posts/{postId}/{userId}")
    public ResponseEntity<Post> likePost(@RequestBody Post post, @PathVariable Long postId, @PathVariable Long userId){
        return ResponseEntity.ok(postService.likePost(post, postId, userId));
    }

    @PutMapping(path = "/posts/dislike/{postId}/{userId}")
    public ResponseEntity<Post> dislikePost(@RequestBody Post post, @PathVariable Long postId, @PathVariable Long userId){
        return ResponseEntity.ok(postService.dislikePost(post, postId, userId));
    }
}
