package com.practice.bookEntries.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;
import lombok.NonNull;

@Document(collection = "users")
@Data
public class User {
    
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String userPassword;
    @DBRef
    List<BookEntry> bookEntries = new ArrayList<>();
}
