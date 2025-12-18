package com.bookbackend.backend.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 실제 테이블명에 맞춰서 변경 (user / User 등)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "login_id", nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 20)
    private String name;
}
