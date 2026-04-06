package com.example.remind_me_server.study.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.remind_me_server.study.infra.persistence.entity.AnswerJpaEntity;

public interface AnswerJpaRepository extends JpaRepository<AnswerJpaEntity, Long> {
    
}
