package com.example.global.error.exception;

import com.example.global.error.ErrorCode;

/**
 * 요청한 리소스를 찾을 수 없을 때 발생하는 예외
 */
public class ResourceNotFoundException extends BaseException {
    
    public ResourceNotFoundException(String resourceName, String identifier) {
        super(ErrorCode.RESOURCE_NOT_FOUND, String.format("%s with identifier %s not found", resourceName, identifier));
    }
    
    public ResourceNotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message);
    }
}
