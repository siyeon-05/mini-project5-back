package com.bookbackend.backend.user.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ResignRequest {
    @NotNull(message = "userId를 입력해야 합니다.")
    @Positive(message = "userId는 1 이상의 값이어야 합니다.")
    private Long userId;

    @NotBlank(message = "비밀번호를 입력받아야합니다.")
    private String password;
}
