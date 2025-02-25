package com.practice.bookEntries.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookEntries.entity.BookEntry;
import com.practice.bookEntries.service.BookEntryService;

@RestController
@RequestMapping("/books")
public class BookEntryController {

    @Autowired
    private BookEntryService service;
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<BookEntry> allEntries = service.getAll();
        if(allEntries != null && !allEntries.isEmpty()){
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<BookEntry> createEntry(@RequestBody BookEntry entry){
        try {
            entry.setDate(LocalDateTime.now());
            service.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<BookEntry> getBookEntryById(@PathVariable ObjectId id){
        Optional<BookEntry> entry = service.findById(id);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateBookEntryById(@PathVariable ObjectId id, @RequestBody BookEntry newEntry){
        BookEntry oldEntry = service.findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setStatus(oldEntry.isStatus() == newEntry.isStatus() ? oldEntry.isStatus(): newEntry.isStatus());
            oldEntry.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().equals("") ? newEntry.getDescription() : oldEntry.getDescription());
            service.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        service.saveEntry(oldEntry);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBookEntryById(@PathVariable ObjectId id){
        service.delelteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
