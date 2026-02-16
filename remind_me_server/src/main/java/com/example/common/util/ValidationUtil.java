package com.example.common.util;

import com.example.common.exception.InvalidInputException;

/**
 * 검증 관련 유틸리티
 */
public final class ValidationUtil {
    
    private ValidationUtil() {
        throw new AssertionError("Cannot instantiate ValidationUtil class");
    }
    
    /**
     * null 체크
     */
    public static void notNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new InvalidInputException(fieldName, "must not be null");
        }
    }
    
    /**
     * 문자열 null 또는 empty 체크
     */
    public static void notBlank(String str, String fieldName) {
        if (str == null || str.isBlank()) {
            throw new InvalidInputException(fieldName, "must not be blank");
        }
    }
    
    /**
     * 수치 범위 체크
     */
    public static void inRange(long value, long min, long max, String fieldName) {
        if (value < min || value > max) {
            throw new InvalidInputException(fieldName, 
                String.format("must be between %d and %d", min, max));
        }
    }
    
    /**
     * 컬렉션 크기 체크
     */
    public static void hasElements(java.util.Collection<?> collection, String fieldName) {
        if (collection == null || collection.isEmpty()) {
            throw new InvalidInputException(fieldName, "must not be empty");
        }
    }
    
    /**
     * 문자열 길이 체크
     */
    public static void lengthInRange(String str, int min, int max, String fieldName) {
        notBlank(str, fieldName);
        if (str.length() < min || str.length() > max) {
            throw new InvalidInputException(fieldName, 
                String.format("length must be between %d and %d", min, max));
        }
    }
}
