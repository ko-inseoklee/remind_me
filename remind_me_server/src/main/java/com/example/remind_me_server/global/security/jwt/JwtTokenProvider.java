package com.example.remind_me_server.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.remind_me_server.auth.application.dto.UserJwtClaimDTO;
import com.example.remind_me_server.global.error.exception.CustomJwtException;

import java.util.Optional;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final JwtParser jwtParser;
    private final long validityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
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

    public Authentication getAuthentication(String token) {
    // 1. 이미 구현된 getUserId를 활용해 userId 추출
    // (이미 파싱 로직이 포함되어 있으므로 재사용하거나, 중복 파싱이 싫다면 아래처럼 작성)
    Claims claims = jwtParser.parseSignedClaims(token).getPayload();
    
    Long userId = Optional.ofNullable(claims.getSubject())
            .map(Long::valueOf)
            .orElseThrow(() -> new JwtException("Subject is missing in token"));

    // 2. 권한 정보 설정 (현재는 빈 값, 필요 시 claims에서 추출)
    List<SimpleGrantedAuthority> authorities = Collections.emptyList();

    // 3. UsernamePasswordAuthenticationToken 생성
    // principal: userId(Long), credentials: null(이미 인증됨), authorities: 권한 리스트
    return new UsernamePasswordAuthenticationToken(userId, null, authorities);
}

}
