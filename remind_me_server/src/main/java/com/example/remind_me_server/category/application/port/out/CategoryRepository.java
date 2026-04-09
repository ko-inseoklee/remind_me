package com.example.remind_me_server.category.application.port.out;

import com.example.remind_me_server.category.domain.Category;
import com.example.remind_me_server.global.repository.BaseRepository;


//TIL(26.04.09): 인터페이스는 실행 시 classLoader에 의해 class파일로는 들어오지만, 스프링의 프록시 전략으로 인해 구현체 하나만 메모리에 객체로 올라감.
public interface CategoryRepository extends BaseRepository<Category> {
    
}
