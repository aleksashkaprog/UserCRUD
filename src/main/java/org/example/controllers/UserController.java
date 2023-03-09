package org.example.controllers;


import org.example.models.User;


import org.example.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> list() {

        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for(User user : userIterable) {
            users.add(user);
        }

        return users;
    }

    @PostMapping( "/users")
    public int add (User user) {

        User newUser = userRepository.save(user);
        return newUser.getId();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalUser.get(), HttpStatus.OK);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity put(@PathVariable int id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User updateUser = userRepository.save(user);
        return new ResponseEntity(updateUser, HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success delete");

    }
}
