package com.bookbackend.backend.user.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    // getter 들
    private String status;
    private String message;
    private T data;

    // 생성자
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
