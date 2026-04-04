package com.example.remind_me_server.category.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.example.remind_me_server.category.domain.Category;
import com.example.remind_me_server.category.infrastructure.entity.CategoryJpaEntity;

@Component
public class CategoryMapper {

    // Entity -> Domain (조회 시)
    public Category toDomain(CategoryJpaEntity entity) {
        if (entity == null) return null;

        return new Category(
            entity.getId(),
            entity.getName()
        );
    }

    // Domain -> Entity (저장 시)
    public CategoryJpaEntity toEntity(Category domain) {
        if (domain == null) return null;

        // JPA 엔티티는 가변 객체이므로 빌더나 세터를 활용
        return CategoryJpaEntity.builder()
            .name(domain.name())
            .build();
    }
}