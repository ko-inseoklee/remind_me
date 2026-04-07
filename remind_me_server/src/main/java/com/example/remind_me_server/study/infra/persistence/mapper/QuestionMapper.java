package com.example.remind_me_server.study.infra.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.remind_me_server.global.jpa.CustomMapper;
import com.example.remind_me_server.study.domain.Question;
import com.example.remind_me_server.study.infra.persistence.entity.QuestionJpaEntity;
import com.example.remind_me_server.user.infra.entity.UserJpaEntity;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionMapper implements CustomMapper<Question, QuestionJpaEntity> {

    private final EntityManager em;

    @Override
    public Question toDomain(QuestionJpaEntity entity) {
        if (entity == null) return null;

        return new Question(
            entity.getId(),
            entity.getContent(),
            null, // categoryId는 QuestionJpaEntity에 없으므로 null로 설정
            entity.getUser().getId()
        );
    }
    @Override
    public QuestionJpaEntity toEntity(Question domain) {
        if (domain == null) return null;

        return QuestionJpaEntity.builder()
            .content(domain.content())
            // categoryId는 QuestionJpaEntity에 없으므로 설정하지 않음
            .user(em.getReference(UserJpaEntity.class, domain.userId())) // userId로 UserJpaEntity를 조회하여 설정해야 하지만, 여기서는 null로 설정
            .build();
    }

        public Iterable<Question> toDomainList(List<QuestionJpaEntity> entities) {
        return entities.stream().map(this::toDomain).toList();
    }
}