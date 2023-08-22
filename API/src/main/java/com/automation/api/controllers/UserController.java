package com.automation.api.controllers;

import com.automation.api.models.User;
import com.automation.api.services.UserService;
import com.automation.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Utils utils;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping(value = "/api/add/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User checkUser = userService.findUserByEmail(newUser.getEmail());
        if (checkUser != null) {
            return (ResponseEntity<User>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        newUser.setPassword(utils.encrpytPassword(newUser.getPassword()));
        newUser = userService.addUser(newUser);
        return ResponseEntity.ok().body(newUser);
    }
}
