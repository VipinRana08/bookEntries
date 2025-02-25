package com.practice.bookEntries.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.bookEntries.entity.BookEntry;

public interface BookEntryRepository extends MongoRepository<BookEntry, ObjectId>{

}
