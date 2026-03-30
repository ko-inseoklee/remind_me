package com.example.global.error.exception;

import com.example.global.error.ErrorCode;

/**
 * 비즈니스 로직 처리 중 발생하는 일반적인 예외
 */
public class BusinessException extends BaseException {
    
    
    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
    
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
