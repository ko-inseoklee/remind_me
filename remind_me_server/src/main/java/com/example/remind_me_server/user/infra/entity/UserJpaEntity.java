package com.example.remind_me_server.user.infra.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.remind_me_server.global.jpa.Password;
import com.example.remind_me_server.global.jpa.entity.BaseEntityWithId;
import com.example.remind_me_server.user.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;
import lombok.*;

/**
 * 순수 도메인 엔티티 (Persistence Ignorance)
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Builder
public class UserJpaEntity extends BaseEntityWithId {
    private final Long id;
    
    private final String email;


    @Embedded
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Password password;

    private String nickname;
    private UserRole role;


    public void changeNickname(String newNickname) {
        if (newNickname == null || newNickname.isBlank()) throw new IllegalArgumentException();
        this.nickname = newNickname;
    }

    public boolean authenticate(String rawPassword, PasswordEncoder encoder) {
        return this.password.isMatch(rawPassword, encoder);
    }
}