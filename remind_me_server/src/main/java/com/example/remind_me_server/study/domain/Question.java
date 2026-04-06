package com.example.remind_me_server.study.domain;

public record Question(
    Long id,
    String content,
    Long categoryId,
    Long userId
) {
}
