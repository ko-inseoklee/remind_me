package com.example.common.infrastructure.persistence.jpa.repository.user;

import com.example.common.infrastructure.persistence.jpa.entity.UserJpaEntity;
import com.example.common.infrastructure.persistence.jpa.repository.base.BaseRepository;

public interface UserRepository extends BaseRepository<UserJpaEntity, Long> {
    
    boolean existsByEmail(String email);

    
}