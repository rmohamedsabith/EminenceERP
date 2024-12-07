package com.example.emipos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T data;

    // Constructor for error responses with status code and error message
    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    // Static method for creating success responses
    public static <T> ApiResponse<T> success(int statusCode, String message, T data) {
        return new ApiResponse<>(statusCode, message, data);
    }

    // Static method for creating error responses
    public static <T> ApiResponse<T> error(int statusCode, String message) {
        return new ApiResponse<>(statusCode, message);
    }

    // Static method for creating error responses with custom data
    public static <T> ApiResponse<T> error(int statusCode, String message, T data) {
        return new ApiResponse<>(statusCode, message, data);
    }
}
