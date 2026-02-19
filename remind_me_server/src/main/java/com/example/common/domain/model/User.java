package com.example.common.domain.model;

import lombok.*;
import java.time.LocalDateTime;

/**
 * 순수 도메인 엔티티 (Persistence Ignorance)
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {
    private final Long id;
    private final String email;
    private String password;
    private String nickname;
    private Role role;
    
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public enum Role {
        USER, ADMIN
    }

    public void changeNickname(String newNickname) {
        if (newNickname == null || newNickname.isBlank()) throw new IllegalArgumentException();
        this.nickname = newNickname;
    }
}