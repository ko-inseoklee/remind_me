package com.example.common.web.exception;

/**
 * 인증 관련 예외
 */
public class UnauthorizedException extends BaseException {
    
    public UnauthorizedException(String message) {
        super(401, message);
    }
}
