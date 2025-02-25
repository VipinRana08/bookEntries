package com.practice.bookEntries.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "book_entries")
@Getter
@Setter
public class BookEntry {
    
    @Id
    private ObjectId id;
    private String title;
    private String genre;
    private String author;
    private String description;
    private boolean status;
    private LocalDateTime date;
    
}
