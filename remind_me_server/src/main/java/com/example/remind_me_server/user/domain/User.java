package com.example.remind_me_server.user.domain;

public record User(
    Long id,
    String email,
    String password,
    String nickname,
    Role role
) {

    public enum Role {
        USER, ADMIN
    }
}
