package com.siedla.socialnetwork.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "posts")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
    private LocalDate postDate;
    private LocalTime postTime;
    private Long likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("posts")
    private User user;

    @ManyToMany(mappedBy = "likedPosts")
    private List<User> likedBy = new LinkedList<>();

    public Post(String description, LocalDate postDate, LocalTime postTime, Long likes) {
        this.description = description;
        this.postDate = postDate;
        this.postTime = postTime;
        this.likes = likes;
    }

    public Post(String description, LocalDate postDate, LocalTime postTime, Long likes, User user) {
        this.description = description;
        this.postDate = postDate;
        this.postTime = postTime;
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

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public LocalTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalTime postTime) {
        this.postTime = postTime;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    //@JsonManagedReference
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
