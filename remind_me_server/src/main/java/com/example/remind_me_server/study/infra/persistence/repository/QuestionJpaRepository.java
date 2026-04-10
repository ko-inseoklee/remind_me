package com.example.remind_me_server.study.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.remind_me_server.study.infra.persistence.entity.QuestionJpaEntity;

public interface QuestionJpaRepository extends JpaRepository<QuestionJpaEntity, Long> {
    
}
