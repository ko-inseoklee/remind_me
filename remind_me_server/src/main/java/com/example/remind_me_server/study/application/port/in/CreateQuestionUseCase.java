package com.example.remind_me_server.study.application.port.in;

import com.example.remind_me_server.study.domain.Question;

public interface CreateQuestionUseCase {
    
    public Question create(CreateQuestionCommand command);
}
