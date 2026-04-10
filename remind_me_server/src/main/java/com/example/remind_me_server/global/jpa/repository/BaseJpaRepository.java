package com.example.remind_me_server.global.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 기본 CRUD 기능을 제공하는 기본 Repository 인터페이스
 * 나중에 다른 앱에서도 상속받아 사용 가능
 * 
 * @param <T> 엔티티 타입
 * @param <ID> 식별자 타입
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID> {
    
    /**
     * 엔티티가 존재하는지 확인
     */
    boolean existsById(ID id);
    
    /**
     * ID로 엔티티 조회 (null safe)
     */
    T findByIdOrElseThrow(ID id, Class<? extends RuntimeException> exceptionClass);
}
