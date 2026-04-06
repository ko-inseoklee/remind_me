package com.example.remind_me_server.study.domain;

public record Answer(
    Long id,
    String content,
    Long questionId
) {


}
