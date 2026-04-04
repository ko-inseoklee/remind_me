package com.example.remind_me_server.category.infrastructure.entity;


import com.example.remind_me_server.global.jpa.entity.BaseEntityWithId;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "category")
@Getter
@Builder
public class CategoryJpaEntity extends BaseEntityWithId {
    private String name;
}
