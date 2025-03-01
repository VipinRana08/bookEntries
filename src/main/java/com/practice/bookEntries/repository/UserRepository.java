package com.practice.bookEntries.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.bookEntries.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByUserName(String userName);
    void deleteUserByUserName(String userName);
}
