package com.example.remind_me_server.user.infra.mapper;

import com.example.remind_me_server.global.jpa.CustomMapper;
import com.example.remind_me_server.user.domain.User;
import com.example.remind_me_server.user.infra.entity.UserJpaEntity;

public class UserMapper implements CustomMapper<User, UserJpaEntity> {

    @Override
    public User toDomain(UserJpaEntity entity) {
        if (entity == null) return null;

        return new User(
            entity.getId(),
            entity.getEmail(),
            entity.getNickname(),
            entity.getRole()
        );
    }

    @Override
    public UserJpaEntity toEntity(User domain) {

        return UserJpaEntity.builder()
            .email(domain.email())
            .nickname(domain.nickname())
            .role(domain.role())
            .build();
    }
    
}
