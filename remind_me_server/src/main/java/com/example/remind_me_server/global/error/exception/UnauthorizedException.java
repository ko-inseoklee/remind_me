package com.example.remind_me_server.global.error.exception;

import com.example.remind_me_server.global.error.ErrorCode;

/**
 * 인증 관련 예외
 */
public class UnauthorizedException extends BaseException {
    
    public UnauthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
    }
}
