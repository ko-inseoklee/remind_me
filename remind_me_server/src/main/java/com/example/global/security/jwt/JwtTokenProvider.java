package com.example.common.infrastructure.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Date;

import javax.crypto.SecretKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey key;
    private final JwtParser jwtParser;
    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        // 1. 키 생성 (Base64 디코딩 필수)
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        // 2. 파서 사전 빌드 (성능 및 문법 최적화)
        this.jwtParser = Jwts.parser()
                .verifyWith(key)
                .build();
    }

    public String createToken(UserJwtClaimDTO user) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.
            builder()
                .claim("email", user.getEmail())
                .claim("nickname", user.getNickname())
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
            .compact();
    }

    public Long getUserId(String token) {
       String subject = jwtParser
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();

        try {
            // null 체크와 숫자 형식 체크를 동시에 수행
            return Optional.ofNullable(subject)
                    .map(Long::valueOf)
                    .orElseThrow(() -> new JwtException("Subject is missing"));
        } catch (NumberFormatException e) {
            // 타입이 맞지 않는 경우도 '유효하지 않은 토큰'으로 간주
            throw new JwtException("Invalid user ID format in token");
        }
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
        log.error("토큰 만료: {}", e.getMessage());
        throw new CustomJwtException("RENEW_TOKEN"); // 클라이언트에게 재발급 유도
        } catch (SignatureException e) {
            log.error("서명 위조 감지: {}", e.getMessage());
            throw new SecurityException("INVALID_SIGNATURE");
        } catch (JwtException e) {
            log.error("유효하지 않은 토큰: {}", e.getMessage());
            throw new SecurityException("INVALID_TOKEN");
        }
    }   
}
