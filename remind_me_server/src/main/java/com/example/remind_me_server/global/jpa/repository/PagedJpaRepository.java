package com.example.remind_me_server.global.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 페이지네이션 기능을 추가한 Repository 인터페이스
 * 
 * @param <T> 엔티티 타입
 * @param <ID> 식별자 타입
 */
@NoRepositoryBean
public interface PagedRepository<T, ID> extends BaseRepository<T, ID> {
    
    /**
     * 페이지 단위로 모든 엔티티 조회
     */
    Page<T> findAll(Pageable pageable);
}
