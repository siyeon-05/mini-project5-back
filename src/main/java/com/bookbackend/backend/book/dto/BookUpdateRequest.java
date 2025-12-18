package com.bookbackend.backend.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @NotBlank(message = "책 제목을 입력하세요")
    @Size(max = 50, message = "책 제목은 50자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "책 내용을 입력해주세요")
    private String content;
    @NotBlank(message = "장르를 입력해주세요")
    private String genre;
    @NotBlank(message = "저자를 입력해주세요")
    @Size(max = 20, message = "20자 이상 초과할 수 없습니다.")
    private String author;
    //@NotBlank(message = "url이 존재하지 않습니다.")
    //private String imageUrl;
}
