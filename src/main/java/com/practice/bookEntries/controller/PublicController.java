package com.practice.bookEntries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    } 

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
