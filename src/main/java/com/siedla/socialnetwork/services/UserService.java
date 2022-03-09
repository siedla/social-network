package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getUsers();
    User getUserByEmail(String email);
    User addFriend(User user, Long userId, Long friendId);
}
