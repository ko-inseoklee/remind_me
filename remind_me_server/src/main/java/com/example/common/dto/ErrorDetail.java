package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 에러 응답 상세 정보
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {
    
    private String field;
    private String message;
    private Object rejectedValue;
    
    public static ErrorDetail of(String field, String message) {
        return ErrorDetail.builder()
            .field(field)
            .message(message)
            .build();
    }
    
    public static ErrorDetail of(String field, String message, Object rejectedValue) {
        return ErrorDetail.builder()
            .field(field)
            .message(message)
            .rejectedValue(rejectedValue)
            .build();
    }
}
