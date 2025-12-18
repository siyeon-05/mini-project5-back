package com.bookbackend.backend.book.entity;

import com.bookbackend.backend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "book",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "title", "author"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(length = 50, nullable = false)
    private String title;

    private String content;

    @Column(length = 50)
    private String genre;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}