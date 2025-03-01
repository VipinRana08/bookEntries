package com.practice.bookEntries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.repository.UserRepository;
import com.practice.bookEntries.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;

    // @GetMapping
    // public List<User> getAllUser(){
    //     return service.getAll();
    // }
    

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User userInDb = service.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setUserPassword(user.getUserPassword());
        service.saveEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        repository.deleteUserByUserName(auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
