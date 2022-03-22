package com.siedla.socialnetwork.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    private LocalDateTime postDate;
    private Long likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"friends", "posts", "likedPosts"})
    private User user;

    @ManyToMany(mappedBy = "likedPosts")
    private List<User> likedBy = new LinkedList<>();

    public Post(String description, LocalDateTime postDate, Long likes) {
        this.description = description;
        this.postDate = postDate;
        this.likes = likes;
    }

    public Post(String description, LocalDateTime postDate, Long likes, User user) {
        this.description = description;
        this.postDate = postDate;
        this.likes = likes;
        this.user = user;
    }

    public Post(Long id, String description, User user) {
        this.description = description;
        this.user = user;
        this.id = id;
    }

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

}
