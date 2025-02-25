package com.practice.bookEntries.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BookEntry> getAll(){
        return service.getAll();
    }

    @PostMapping
    public BookEntry createEntry(@RequestBody BookEntry entry){
        entry.setDate(LocalDateTime.now());
        service.saveEntry(entry);
        return entry;
    }

    @GetMapping("id/{id}")
    public BookEntry getBookEntryById(@PathVariable ObjectId id){
        return service.findById(id).orElse(null);
    }

    @PutMapping("id/{id}")
    public BookEntry updateBookEntryById(@PathVariable ObjectId id, @RequestBody BookEntry newEntry){
        BookEntry oldEntry = service.findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().equals("") ? newEntry.getDescription() : oldEntry.getDescription());
        }
        service.saveEntry(oldEntry);
        return oldEntry;
    }
    @DeleteMapping("id/{id}")
    public String deleteBookEntryById(@PathVariable ObjectId id){
        service.delelteById(id);
        return "Deleted";
    }
}
