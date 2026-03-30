package com.example.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Auth
    UNAUTHORIZED(401, "AU001", "인증이 필요합니다."),
    EXPIRED_TOKEN(401, "AU002", "액세스 토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "AU003", "유효하지 않은 토큰입니다."),
    INVALID_SIGNATURE(401, "AU004", "서명이 유효하지 않습니다."),
    FORBIDDEN(403, "AU005", "권한이 없습니다."),

    // Common
    INVALID_INPUT(400, "CM001", "잘못된 입력입니다."),
    RESOURCE_NOT_FOUND(404, "CM002", "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "CM003", "서버 내부 오류입니다."),

    // User
    USER_NOT_FOUND(404, "US001", "존재하지 않는 사용자입니다.");

    private final int status;
    private final String code;
    private final String message;
}