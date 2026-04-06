package com.example.remind_me_server.study.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.example.remind_me_server.global.jpa.CustomMapper;
import com.example.remind_me_server.study.domain.Answer;
import com.example.remind_me_server.study.infra.persistence.entity.AnswerJpaEntity;
import com.example.remind_me_server.study.infra.persistence.entity.QuestionJpaEntity;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnswerMapper implements CustomMapper<Answer, AnswerJpaEntity> {

    private final EntityManager em;

    @Override
    public Answer toDomain(AnswerJpaEntity entity) {
        if (entity == null) return null;
        return new Answer(
            entity.getId(),
            entity.getContent(),
            entity.getQuestion().getId() // questionId는 QuestionJpaEntity에서 가져와야 함
        );
    }

    @Override
    public AnswerJpaEntity toEntity(Answer domain) {
        if (domain == null) return null;
        return AnswerJpaEntity.builder()
            .content(domain.content())
            .question(em.getReference(QuestionJpaEntity.class, domain.questionId()))
            .build();
    
    }
}