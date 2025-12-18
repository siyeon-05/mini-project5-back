package com.bookbackend.backend.book.dto;

import com.bookbackend.backend.book.entity.Book;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDetailResponse {
    private Long bookId;
    private String title;
    private String content;
    private String genre;
    private String author;
    private String imageUrl;

    public static BookDetailResponse of(Book book) {
        BookDetailResponse dto = new BookDetailResponse();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setContent(book.getContent());
        dto.setGenre(book.getGenre());
        dto.setAuthor(book.getAuthor());
        dto.setImageUrl(book.getImageUrl());

        return dto;
    }
}
