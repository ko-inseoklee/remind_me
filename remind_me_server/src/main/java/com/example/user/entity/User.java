package com.example.user.entity;

import lombok.*;

import com.example.global.jpa.entity.base.BaseEntityWithId;

/**
 * 순수 도메인 엔티티 (Persistence Ignorance)
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseEntityWithId{
    private final Long id;
    private final String email;
    private String password;
    private String nickname;
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public void changeNickname(String newNickname) {
        if (newNickname == null || newNickname.isBlank()) throw new IllegalArgumentException();
        this.nickname = newNickname;
    }
}