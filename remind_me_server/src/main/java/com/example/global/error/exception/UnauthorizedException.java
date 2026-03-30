package com.example.global.error.exception;

import com.example.global.error.ErrorCode;

/**
 * 인증 관련 예외
 */
public class UnauthorizedException extends BaseException {
    
    public UnauthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
    }
}
