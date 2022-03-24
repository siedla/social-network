package com.siedla.socialnetwork.controllers;

import com.siedla.socialnetwork.model.SimpleUser;
import com.siedla.socialnetwork.model.User;
import com.siedla.socialnetwork.services.FileLocationService;
import com.siedla.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final FileLocationService fileLocationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, FileLocationService fileLocationService) {
        this.userService = userService;
        this.fileLocationService = fileLocationService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping(path = "/users/friends/{id}")
    public ResponseEntity<List<User>> getUserFriends(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserFriends(id));
    }

    @PutMapping("/users/{userId}/{friendId}")
    public ResponseEntity<User> addFriend(@PathVariable Long userId, @PathVariable Long friendId, @RequestBody User user) {
        return ResponseEntity.ok(userService.addFriend(user, userId, friendId));

    }

    @GetMapping("/users/{firstName}/{lastName}")
    public ResponseEntity<List<User>> findUserByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return ResponseEntity.ok(userService.findUserByFirstAndLastName(firstName, lastName));
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SimpleUser simpleUser) {
        User newUser = new User(simpleUser.getFirstName(), simpleUser.getLastName(),
                simpleUser.getEmail(), passwordEncoder.encode(simpleUser.getPassword()));

        return ResponseEntity.ok(userService.addNewUser(newUser));
    }

    @PostMapping("/users/image/{userId}")
    public ResponseEntity<User> addImage(@RequestParam("file") MultipartFile image, @PathVariable Long userId) throws Exception {
        String imagePath = fileLocationService.save(image.getBytes(), image.getOriginalFilename());
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(userService.addImage(user, imagePath));
    }

}
