package com.practice.bookEntries.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList("USER"));
        repository.save(user);
    }
    public void saveEntryForAdmin(User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        repository.save(user);
    }

    public void saveUser(User user){
        repository.save(user);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public Optional<?> findById(ObjectId id){
        return repository.findById(id);
    }

    public void delelteById(ObjectId id){
        repository.deleteById(id);
    }
    public User findByUserName(String userName){
        return repository.findByUserName(userName);
    }
}
