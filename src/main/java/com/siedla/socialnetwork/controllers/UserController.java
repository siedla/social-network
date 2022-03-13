package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.SimpleUser;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/users/{userId}/{friendId}")
    public User addFriend(@PathVariable Long userId, @PathVariable Long friendId, @RequestBody User user) {
        return userService.addFriend(user, userId, friendId);
    }

    @GetMapping("/users/{firstName}/{lastName}")
    public List<User> findUserByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return userService.findUserByFirstAndLastName(firstName, lastName);
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody SimpleUser simpleUser) {
        User newUser = new User(simpleUser.getFirstName(), simpleUser.getLastName(),
                simpleUser.getEmail(), passwordEncoder.encode(simpleUser.getPassword()));

        return userService.addNewUser(newUser);
    }

}
