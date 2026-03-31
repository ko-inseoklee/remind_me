package com.example.remind_me_server.global.web.constant;

/**
 * HTTP 상태 코드 상수
 */
public final class HttpStatusCode {
    
    private HttpStatusCode() {
        throw new AssertionError("Cannot instantiate HttpStatusCode class");
    }
    
    // 2xx Success
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    
    // 4xx Client Error
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int CONFLICT = 409;
    
    // 5xx Server Error
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;
}
