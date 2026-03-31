package com.example.remind_me_server.global.error.exception;

import com.example.remind_me_server.global.error.ErrorCode;

/**
 * 유효하지 않은 입력값에 대한 예외
 */
public class InvalidInputException extends BaseException {
    
    public InvalidInputException(String fieldName, String reason) {
        super(ErrorCode.INVALID_INPUT, String.format("Invalid input for field '%s': %s", fieldName, reason));
    }
    
    public InvalidInputException(String message) {
        super(ErrorCode.INVALID_INPUT, message);
    }
}
