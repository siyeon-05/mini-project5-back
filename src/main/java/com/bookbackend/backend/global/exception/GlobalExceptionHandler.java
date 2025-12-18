package com.bookbackend.backend.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode error = ex.getErrorCode();

        ErrorResponse response = ErrorResponse.builder()
                .status(error.getStatus().value())
                .message(error.getMessage())
                .build();

        return ResponseEntity.status(error.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> handleMissingHeader(MissingRequestHeaderException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .message("Authorization 헤더가 필요합니다.")
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials() {

        ErrorResponse response = ErrorResponse.builder()
                .status(401)
                .message("아이디 또는 비밀번호가 올바르지 않습니다.")
                .build();

        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied() {

        ErrorResponse response = ErrorResponse.builder()
                .status(403)
                .message("접근 권한이 없습니다.")
                .build();

        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Unhandled exception", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(500)
                .message("서버 내부 오류가 발생했습니다.")
                .build();

        return ResponseEntity.status(500).body(response);
    }
}
