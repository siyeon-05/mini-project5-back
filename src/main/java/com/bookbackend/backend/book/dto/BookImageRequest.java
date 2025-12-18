package com.bookbackend.backend.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookImageRequest {
    @NotBlank(message = "url이 존재하지 않습니다.")
    private String imageUrl;
}
