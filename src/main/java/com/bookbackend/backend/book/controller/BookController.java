package com.bookbackend.backend.book.controller;

import com.bookbackend.backend.book.dto.*;
import com.bookbackend.backend.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // 책 등록
    @Operation(summary = "책 등록", description = "특정 사용자가 새로운 책을 등록합니다.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BookDetailResponse createBook(
            @RequestParam Long userId,
            @RequestPart("book") @Valid BookCreateRequest request,
            @RequestPart("image") MultipartFile image
    ) {
        return bookService.createBook(userId, request, image);
    }

    // 사용자별 책 목록 조회
    @Operation(summary = "사용자별 책 목록", description = "특정 사용자가 등록한 책 목록을 조회합니다.")
    @GetMapping
    public List<BookListResponse> getBooks(
            @Parameter(description = "조회할 사용자 ID", example = "1")
            @RequestParam Long userId
    ) {
        return bookService.getBooksByUser(userId);
    }

    // 책 상세 조회
    @Operation(summary = "책 상세 조회", description = "책 ID로 상세 정보를 조회합니다.")
    @GetMapping("/{bookId}")
    public BookDetailResponse getBook(
            @Parameter(description = "조회할 책 ID", example = "10")
            @PathVariable Long bookId
    ) {
        return bookService.getBook(bookId);
    }

    // 책 수정
    @PutMapping(value = "/{bookId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BookDetailResponse updateBook(
            @PathVariable Long bookId,
            @RequestPart("data") @Valid BookUpdateRequest request,
            @RequestPart("image") MultipartFile image
    ) {
        return bookService.updateBook(bookId, request, image);
    }

    // 책 삭제
    @Operation(summary = "책 삭제", description = "책 ID로 책을 삭제합니다.")
    @DeleteMapping("/{bookId}")
    public void deleteBook(
            @Parameter(description = "삭제할 책 ID", example = "10")
            @PathVariable Long bookId
    ) {
        bookService.deleteBook(bookId);
    }
    //책 검색
    @Operation(summary = "책 검색", description = "책 title로 책 검색")
    @GetMapping("/titlesearch")
    public List<BookListResponse> searchBooks(@RequestParam String title){
        return bookService.searchBooksByTitle(title);
    }
    //모든 유저 책 목록
    @Operation(summary = "전체 책 목록 조회", description = "모든 사용자가 작성한 책 목록을 조회")
    @GetMapping("/all")
    public List<BookListResponse> getAllBooks(){
        return bookService.getAllBooks();
    }
    //책 표지 url 추가
    @Operation(summary = "책 표지 수정", description = "책 표지 이미지를 업로드하여 변경")
    @PutMapping(
            value = "/{bookId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public BookDetailResponse updateBookImage(
            @PathVariable Long bookId,
            @RequestPart("image") MultipartFile image
    ) {
        return bookService.updateBookImage(bookId, image);
    }
}