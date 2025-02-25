package com.practice.bookEntries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUser(){
        return service.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        service.saveEntry(user);
    }

    public void updateUser(@RequestBody User user){
        User userInDb = service.findByUserName(user.getUserName());
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setUserPassword(user.getUserPassword());
            service.saveEntry(userInDb);
        }
    }
}
