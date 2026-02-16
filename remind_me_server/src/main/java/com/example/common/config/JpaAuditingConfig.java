package com.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing 활성화 설정
 * BaseEntity의 @CreatedDate, @LastModifiedDate를 자동으로 처리
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
