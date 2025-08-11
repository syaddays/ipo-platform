package com.ipoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard API response wrapper
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Creates a success response with data
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Operation successful")
                .data(data)
                .build();
    }

    /**
     * Creates a success response with message and data
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Creates an error response with message
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
    
    /**
     * Creates an error response with message and data
     */
    public static <T> ApiResponse<T> error(String message, T data) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(data)
                .build();
    }
} 