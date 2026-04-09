package com.example.remind_me_server.study.application.service;

import org.springframework.stereotype.Service;

import com.example.remind_me_server.study.application.port.in.CreateQuestionCommand;
import com.example.remind_me_server.study.application.port.in.CreateQuestionUseCase;
import com.example.remind_me_server.study.application.port.out.AnswerRepository;
import com.example.remind_me_server.study.application.port.out.QuestionRepository;
import com.example.remind_me_server.study.domain.Question;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreateQuestionService implements CreateQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void create(CreateQuestionCommand command) {
        Question question = new Question(null, null, null)
    }
    
}
