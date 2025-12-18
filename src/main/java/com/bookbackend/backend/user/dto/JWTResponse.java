package com.bookbackend.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
}
