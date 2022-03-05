package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> getUsers() {
        Set<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }
}
