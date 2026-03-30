package com.example.common.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 모든 API 응답의 표준 래퍼
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    private boolean success;
    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    
    /**
     * 성공 응답
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
            .success(true)
            .statusCode(200)
            .message(message)
            .data(data)
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Success");
    }
    
    public static ApiResponse<Void> success(String message) {
        return ApiResponse.<Void>builder()
            .success(true)
            .statusCode(200)
            .message(message)
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    /**
     * 실패 응답
     */
    public static <T> ApiResponse<T> failure(int statusCode, String message) {
        return ApiResponse.<T>builder()
            .success(false)
            .statusCode(statusCode)
            .message(message)
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    public static <T> ApiResponse<T> failure(String message) {
        return failure(500, message);
    }
}
