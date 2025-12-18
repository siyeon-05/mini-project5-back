package com.bookbackend.backend.book.repository;

import com.bookbackend.backend.book.entity.Book;
import com.bookbackend.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser(User user);
    List<Book> findByTitleContaining(String keyword);
}
