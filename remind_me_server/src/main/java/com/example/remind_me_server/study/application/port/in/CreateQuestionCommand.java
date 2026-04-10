package com.example.remind_me_server.study.application.port.in;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateQuestionCommand(
    @NotBlank Long userId,
    @NotBlank String content,
    @NotBlank String categoryName,
    @NotEmpty List<String> answerContents
) {
    // [초격차 전략] 컴팩트 생성자를 통한 유효성 검증
    public CreateQuestionCommand {

        if (content.length() > 500) {
            throw new IllegalArgumentException("질문 내용은 500자를 초과할 수 없습니다.");
        }
        // 리스트를 불변 리스트로 방어적 복사
        answerContents = List.copyOf(answerContents);
    }
}