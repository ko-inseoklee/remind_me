package com.example.global.error.exception;

public class CustomJwtException extends RuntimeException {
    public CustomJwtException(String message) {
        super(message);
    }

    public CustomJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomJwtException(Throwable cause) {
        super(cause);
    }

}
