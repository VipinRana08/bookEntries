package com.practice.bookEntries.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.bookEntries.entity.BookEntry;
import com.practice.bookEntries.repository.BookEntryRepository;

@Component
public class BookEntryService {
    
    @Autowired
    private BookEntryRepository repository;

    public void saveEntry(BookEntry entry){
        repository.save(entry);
    }

    public List<BookEntry> getAll(){
        return repository.findAll();
    }

    public Optional<BookEntry> findById(ObjectId id){
        return repository.findById(id);
    }

    public void delelteById(ObjectId id){
        repository.deleteById(id);
    }
}
