package com.example.remind_me_server.global.jpa;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Password {

    @Column(name = "password", nullable = false)
    private String encryptedValue; // 암호화된 비밀번호 저장

    // 비밀번호 검증 로직을 객체 내부에 숨김
    public boolean isMatch(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.encryptedValue);
    }
    
}
