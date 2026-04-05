package com.example.remind_me_server.study.infra.entity;

import com.example.remind_me_server.global.jpa.entity.BaseEntityWithId;
import com.example.remind_me_server.user.infra.entity.UserJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;


@Entity
@Getter
@Builder
public class QuestionJpaEntity extends BaseEntityWithId {

    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
}
