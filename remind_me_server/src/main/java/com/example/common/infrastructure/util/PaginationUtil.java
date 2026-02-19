package com.example.common.util;

/**
 * 페이지네이션 관련 유틸리티
 */
public final class PaginationUtil {
    
    private PaginationUtil() {
        throw new AssertionError("Cannot instantiate PaginationUtil class");
    }
    
    /**
     * 페이지 번호 유효성 검사 및 정규화
     */
    public static int normalizePageNumber(int pageNumber) {
        return Math.max(pageNumber, 0);
    }
    
    /**
     * 페이지 크기 유효성 검사 및 정규화
     */
    public static int normalizePageSize(int pageSize) {
        if (pageSize <= 0) {
            return 20; // 기본값
        }
        return Math.min(pageSize, 100); // 최대 100
    }
    
    /**
     * 전체 페이지 수 계산
     */
    public static int calculateTotalPages(long totalElements, int pageSize) {
        if (pageSize <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalElements / pageSize);
    }
    
    /**
     * 다음 페이지 존재 여부
     */
    public static boolean hasNext(int currentPageNumber, int pageSize, long totalElements) {
        long nextPageStart = (long) (currentPageNumber + 1) * pageSize;
        return nextPageStart < totalElements;
    }
}
