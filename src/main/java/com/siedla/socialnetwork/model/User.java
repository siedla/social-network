package com.siedla.socialnetwork.model;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=User.class)*/
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.User.firstName}")
    private String firstName;

    @Column
    @NotNull(message="{NotNull.User.lastName}")
    private String lastName;

    @Column
    @NotNull(message="{NotNull.User.email}")
    private String email;

    @Column
    private String password;

    @Column
    private String photoUrl;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Post> posts = new LinkedList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "user")
    private List<User> friends = new LinkedList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "liked_posts", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> likedPosts = new LinkedList<>();


    public User() {
        this.photoUrl = "https://i.ibb.co/cL2QC11/Screenshot-1.png";
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photoUrl = "https://i.ibb.co/cL2QC11/Screenshot-1.png";
        this.password = password;
    }

    public User addPost(Post post) {
        post.setUser(this);
        this.posts.add(post);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
