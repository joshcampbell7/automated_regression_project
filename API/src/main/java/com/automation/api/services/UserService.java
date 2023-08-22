package com.automation.api.services;

import com.automation.api.models.User;
import com.automation.api.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
