package com.example.remind_me_server.user.application.port.respository;

import java.util.Optional;

import com.example.remind_me_server.user.domain.User;



public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User save(User user);
    
    boolean existsByEmail(String email);
}