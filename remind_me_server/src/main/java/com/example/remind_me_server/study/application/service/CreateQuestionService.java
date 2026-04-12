package com.example.remind_me_server.study.application.service;


import org.springframework.stereotype.Service;

import com.example.remind_me_server.category.application.port.out.CategoryRepository;
import com.example.remind_me_server.category.application.service.CategoryManager;
import com.example.remind_me_server.category.domain.Category;
import com.example.remind_me_server.study.application.port.in.CreateQuestionCommand;
import com.example.remind_me_server.study.application.port.in.CreateQuestionUseCase;
import com.example.remind_me_server.study.application.port.out.AnswerRepository;
import com.example.remind_me_server.study.application.port.out.QuestionRepository;
import com.example.remind_me_server.study.domain.Question;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreateQuestionService implements CreateQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CategoryManager categoryService;


    @Transactional
    @Override
    public Question create(CreateQuestionCommand command) {
        Category category = categoryService.getOrCreateCategory(command.categoryName(), command.userId());

        Question question = new Question(null, command.content(), category.id(), command.userId());
        question = questionRepository.save(question);

        return question;   
    }
    
}
