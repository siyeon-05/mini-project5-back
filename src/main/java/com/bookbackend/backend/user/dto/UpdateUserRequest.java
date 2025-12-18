package com.bookbackend.backend.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotNull(message = "userId는 필수적으로 보내주어야 합니다.")
    @Positive(message = "userId는 1 이상의 값이어야 합니다.")
    private Long userId;

    // UserId를 제외한 나머지 내용들은 추가되면 업데이트합니다.
    private String loginId;   // 변경할 로그인 ID (nullable)
    private String password;  // 변경할 비밀번호 (nullable)
    private String name;      // 변경할 이름 (nullable)
}
