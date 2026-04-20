package com.example.remind_me_server.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.remind_me_server.global.security.filter.JwtAuthenticationFilter;
import com.example.remind_me_server.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF & Form Login 비활성화 (REST API 전용)
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)

            // 2. 세션을 사용하지 않음 (Stateless)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // 3. 인가 정책 (URL 별 권한 설정)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll() // 로그인/회원가입은 허용
                .requestMatchers("/api/v1/questions/**").hasRole("USER") // 질문 관련은 USER 권한 필요
                .anyRequest().authenticated() // 나머지는 무조건 인증 필요
            )

            // 4. JWT 필터를 ID/PW 인증 필터 앞에 배치
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
                            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}