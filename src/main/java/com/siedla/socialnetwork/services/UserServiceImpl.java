package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {

        List<User> userList = new LinkedList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User addFriend(User user, Long userId, Long friendId) {
        user.setId(userId);
        Optional<User> optionalUser = userRepository.findById(friendId);
        User friend;
        if(optionalUser.isPresent()){
            friend = optionalUser.get();
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
        userRepository.save(user);
        return user;
    }
    @Override
    public List<User> findUserByFirstAndLastName(String firstName, String lastName){
        return userRepository.findUsersByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean login(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
