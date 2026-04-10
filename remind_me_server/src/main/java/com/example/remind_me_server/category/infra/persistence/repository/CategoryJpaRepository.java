package com.example.remind_me_server.category.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.remind_me_server.category.infra.persistence.entity.CategoryJpaEntity;



public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    Optional<CategoryJpaEntity> findByNameAndUserId(String categoryName, Long userId);
    
}
