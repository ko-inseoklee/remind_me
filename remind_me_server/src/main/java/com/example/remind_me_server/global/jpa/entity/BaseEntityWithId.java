package com.example.remind_me_server.global.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ID를 가진 엔티티의 기본 클래스
 * Long 타입의 ID를 제공
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntityWithId extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityWithId)) return false;
        BaseEntityWithId that = (BaseEntityWithId) o;
        // ID가 null인 경우(영속화 전)에는 동등하지 않다고 판단하거나,
        // 비즈니스 키가 있다면 그것을 사용해야 함. 여기서는 ID 기반 비교.
        // 프록시 객체의 필드에 직접 접근하면 null일 수 있으므로 getter 사용 필수
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        // 영속화 전후 해시코드 일관성을 위해 상수를 반환 (JPA 엔티티 권장 패턴)
        return getClass().hashCode();
    }
}
