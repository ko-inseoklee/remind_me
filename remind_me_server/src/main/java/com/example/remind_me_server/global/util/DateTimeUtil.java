package com.example.remind_me_server.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 날짜/시간 관련 유틸리티
 */
public final class DateTimeUtil {
    
    private DateTimeUtil() {
        throw new AssertionError("Cannot instantiate DateTimeUtil class");
    }
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("HH:mm:ss");
    
    /**
     * 현재 시간
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    /**
     * LocalDateTime을 ISO 형식 문자열로 변환
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : null;
    }
    
    /**
     * LocalDateTime을 DATE 형식 문자열로 변환
     */
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_FORMATTER) : null;
    }
    
    /**
     * LocalDateTime을 TIME 형식 문자열로 변환
     */
    public static String formatTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(TIME_FORMATTER) : null;
    }
    
    /**
     * ISO 형식 문자열을 LocalDateTime으로 파싱
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }
}
