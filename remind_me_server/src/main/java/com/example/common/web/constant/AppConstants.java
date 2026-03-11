package com.example.common.web.constant;

/**
 * 애플리케이션 전체에서 사용되는 상수
 * 나중에 다른 앱에서도 재사용 가능
 */
public final class AppConstants {
    
    private AppConstants() {
        throw new AssertionError("Cannot instantiate AppConstants class");
    }
    
    // ========== API ==========
    public static final String API_PREFIX = "/api";
    public static final String API_V1_PREFIX = "/api/v1";
    
    // ========== Response Messages ==========
    public static final String SUCCESS = "Success";
    public static final String SUCCESS_CREATED = "Resource created successfully";
    public static final String SUCCESS_UPDATED = "Resource updated successfully";
    public static final String SUCCESS_DELETED = "Resource deleted successfully";
    
    public static final String ERROR_INVALID_INPUT = "Invalid input provided";
    public static final String ERROR_NOT_FOUND = "Resource not found";
    public static final String ERROR_INTERNAL = "Internal server error";
    public static final String ERROR_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_FORBIDDEN = "Forbidden";
    
    // ========== Validation ==========
    public static final int MIN_STRING_LENGTH = 1;
    public static final int MAX_STRING_LENGTH = 255;
    public static final int MAX_TEXT_LENGTH = 10000;
    
    // ========== Pagination ==========
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    
    // ========== DateTime ==========
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
}
