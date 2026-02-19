package com.example.common.exception;

/**
 * 모든 비즈니스 예외의 기본 클래스
 * 나중에 다른 앱에서도 재사용 가능하도록 설계
 */
public abstract class BaseException extends RuntimeException {
    
    private final int errorCode;
    private final String errorMessage;
    
    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public BaseException(int errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
