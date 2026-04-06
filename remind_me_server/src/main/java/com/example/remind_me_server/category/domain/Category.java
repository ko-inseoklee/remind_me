package com.example.remind_me_server.category.domain;

import java.util.Objects;

public record Category(Long id, String name, Long userId) {
    public Category {
        Objects.requireNonNull(userId, "userId must not be null");
        
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name must not be null or blank");
        }
    }
}
