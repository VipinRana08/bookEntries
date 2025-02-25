package com.practice.bookEntries.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveEntry(User entry){
        repository.save(entry);
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
