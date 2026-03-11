package com.example.common.web.exception;

/**
 * 유효하지 않은 입력값에 대한 예외
 */
public class InvalidInputException extends BaseException {
    
    public InvalidInputException(String fieldName, String reason) {
        super(400, String.format("Invalid input for field '%s': %s", fieldName, reason));
    }
    
    public InvalidInputException(String message) {
        super(400, message);
    }
}
