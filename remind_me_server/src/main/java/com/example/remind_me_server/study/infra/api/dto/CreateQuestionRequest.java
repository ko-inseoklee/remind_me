package com.example.remind_me_server.study.infra.api.dto;

import java.util.List;

import com.example.remind_me_server.study.application.port.in.CreateQuestionCommand;

public record CreateQuestionRequest(
    String content,
    String categoryName,
    List<String> answerContents
) {
    public CreateQuestionCommand toCommand(Long userId) {
        return new CreateQuestionCommand(userId, content, categoryName, answerContents);
    }
} 