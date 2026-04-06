package com.example.remind_me_server.study.infra.persistence.entity;

import com.example.remind_me_server.global.jpa.entity.BaseEntityWithId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class AnswerJpaEntity extends BaseEntityWithId {

    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionJpaEntity question;
}