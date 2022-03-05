package com.siedla.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
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
    @JsonIgnore
    private User user;

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

    public Post() {

    }

}
