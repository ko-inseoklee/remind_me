package com.example.global.error.exception;

import com.example.global.error.ErrorCode;

/**
 * 권한 관련 예외
 */
public class ForbiddenException extends BaseException {
    
    public ForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN, message);
    }
}
