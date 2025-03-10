package com.practice.bookEntries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.practice.bookEntries.entity.User;
import com.practice.bookEntries.repository.UserRepository;

@Component
public class UserDetailsServiceImplementation implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserName())
            .password(user.getUserPassword())
            .roles(user.getRoles().toArray(new String[0]))
            .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
