package com.example.remind_me_server.global.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 검색 및 필터링 기능을 추가한 Repository 인터페이스
 * 
 * @param <T> 엔티티 타입
 * @param <ID> 식별자 타입
 */
@NoRepositoryBean
public interface SearchableRepository<T, ID> extends PagedRepository<T, ID> {
    
    /**
     * 키워드로 검색 (구체적인 구현은 하위 Repository에서 정의)
     */
    Page<T> search(String keyword, Pageable pageable);
    
    /**
     * 날짜 범위로 필터링
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.createdAt BETWEEN :startDate AND :endDate")
    List<T> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                             @Param("endDate") LocalDateTime endDate);
    
    /**
     * 수정된 엔티티 조회
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.updatedAt BETWEEN :startDate AND :endDate")
    List<T> findByUpdatedDateRange(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);
}
