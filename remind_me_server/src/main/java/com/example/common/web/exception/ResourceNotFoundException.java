package com.example.common.web.exception;

/**
 * 요청한 리소스를 찾을 수 없을 때 발생하는 예외
 */
public class ResourceNotFoundException extends BaseException {
    
    public ResourceNotFoundException(String resourceName, String identifier) {
        super(404, String.format("%s with identifier %s not found", resourceName, identifier));
    }
    
    public ResourceNotFoundException(String message) {
        super(404, message);
    }
}
