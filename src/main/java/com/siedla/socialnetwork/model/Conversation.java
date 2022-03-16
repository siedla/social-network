package com.siedla.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private Long id;

    @ManyToMany(mappedBy = "conversations")
    @JsonIgnoreProperties({"password","posts","friends","likedPosts"})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users = new LinkedList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "conversation")
    @JsonIgnoreProperties("conversation")
    private List<Message> messages = new LinkedList<>();

    public Conversation(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
