package com.example.remind_me_server.user.repository;

import java.util.Optional;

import com.example.remind_me_server.user.entity.User;


public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User save(User user);
    
    boolean existsByEmail(String email);
}