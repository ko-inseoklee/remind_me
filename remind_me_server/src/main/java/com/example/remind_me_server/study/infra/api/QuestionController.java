package com.example.remind_me_server.study.infra.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.remind_me_server.auth.application.LoginUser;
import com.example.remind_me_server.global.web.dto.ApiResponse;
import com.example.remind_me_server.study.application.port.in.CreateQuestionCommand;
import com.example.remind_me_server.study.application.service.CreateQuestionService;
import com.example.remind_me_server.study.domain.Question;
import com.example.remind_me_server.study.infra.api.dto.CreateQuestionRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    
    private final CreateQuestionService createQuestionService;

    @PostMapping("")
    public ApiResponse<Long> createQuestion(
        @LoginUser long userId,
        @RequestBody CreateQuestionRequest request
    ) {

        CreateQuestionCommand command = request.toCommand(userId);

        Question q =  createQuestionService.create(command);
        return ApiResponse.success(q.id(), "생성 성공", HttpStatus.CREATED.value());
    }
    
}
