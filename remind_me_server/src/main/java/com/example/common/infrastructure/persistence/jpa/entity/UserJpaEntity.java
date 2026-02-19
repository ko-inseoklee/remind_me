package com.example.common.infrastructure.persistence.jpa.entity;

import org.checkerframework.checker.units.qual.C;

import com.example.common.domain.model.User;
import com.example.common.infrastructure.persistence.jpa.entity.base.BaseEntityWithId;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserJpaEntity extends BaseEntityWithId {

    @Column(unique = true, nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;


    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private User.Role role;

    @Builder
    public UserJpaEntity(Long id, String email, String password, String nickname, User.Role role) {
        super(id); // BaseJpaEntityWithId의 id 설정
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}

