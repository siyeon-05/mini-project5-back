package com.bookbackend.backend.book.dto;

import com.bookbackend.backend.book.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookListResponse {
    private Long bookId;
    private String title;
    private String genre;
    private String author;
    private String imageUrl;

    public static BookListResponse of(Book book) {
        BookListResponse dto = new BookListResponse();

        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setGenre(book.getGenre());
        dto.setAuthor(book.getAuthor());
        dto.setImageUrl(book.getImageUrl());

        return dto;
    }
}
