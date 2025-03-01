package com.practice.bookEntries.service;

import java.util.List;
import java.util.Optional;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.practice.bookEntries.entity.BookEntry;
import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.repository.BookEntryRepository;

@Component
public class BookEntryService {
    
    @Autowired
    private BookEntryRepository repository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(BookEntry entry, String userName){
        try {
            User user = userService.findByUserName(userName);
            BookEntry savedEntry = repository.save(entry);
            user.getBookEntries().add(savedEntry);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Encounter error in save transaction: " + e);
        }
    }
    public void saveEntry(BookEntry entry){
        repository.save(entry);
    }

    public List<BookEntry> getAll(){
        return repository.findAll();
    }

    public Optional<BookEntry> findById(ObjectId id){
        return repository.findById(id);
    }

    @Transactional
    public boolean delelteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getBookEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                repository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occured while deletion: " , e);
        }
        return removed;
    }
}
