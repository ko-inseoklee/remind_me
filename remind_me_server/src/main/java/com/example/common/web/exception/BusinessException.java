package com.example.common.web.exception;

/**
 * 비즈니스 로직 처리 중 발생하는 일반적인 예외
 */
public class BusinessException extends BaseException {
    
    public BusinessException(String message) {
        super(400, message);
    }
    
    public BusinessException(int errorCode, String message) {
        super(errorCode, message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(400, message, cause);
    }
}
