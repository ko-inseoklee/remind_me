package com.example.common.web.exception;

/**
 * 권한 관련 예외
 */
public class ForbiddenException extends BaseException {
    
    public ForbiddenException(String message) {
        super(403, message);
    }
}
