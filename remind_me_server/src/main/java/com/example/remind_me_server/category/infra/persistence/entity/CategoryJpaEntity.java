package com.example.remind_me_server.category.infra.persistence.entity;


import com.example.remind_me_server.global.jpa.entity.BaseEntityWithId;
import com.example.remind_me_server.user.infra.entity.UserJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "category")
@Getter
@Builder
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_category_user_name", // 인덱스 이름 (관리 용이성)
            columnNames = {"user_id", "name"} // 복합 키 구성 컬럼
        )
    }
)
public class CategoryJpaEntity extends BaseEntityWithId {

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;
}
