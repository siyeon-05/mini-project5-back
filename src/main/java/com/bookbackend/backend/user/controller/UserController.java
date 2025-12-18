package com.bookbackend.backend.user.controller;


import com.bookbackend.backend.user.dto.*;
import com.bookbackend.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User API", description = "사용자 회원가입, 로그인, 수정, 탈퇴 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "로그인", description = "loginId와 password를 받아 JWT AccessToken / RefreshToken을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JWTResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {
        JWTResponse response = userService.login(request);

        return ResponseEntity.ok(
                new ApiResponse<>("200", "로그인 성공", response)
        );
    }

    @Operation(
            summary = "회원가입",
            description = "사용자가 loginId, password, name을 입력하여 회원가입을 수행합니다."
    )
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JWTResponse>> signup(
            @Valid @RequestBody SignUpRequest request
    ) {
        JWTResponse response = userService.signup(request);

        return ResponseEntity.ok(
                new ApiResponse<>("200", "회원가입 성공", response)
        );
    }

    @Operation(
            summary = "회원 탈퇴",
            description = "userId + password를 입력하여 회원 탈퇴를 수행합니다."
    )
    @DeleteMapping("/resign")
    public ResponseEntity<ApiResponse<ResignResponse>> resign(
            @Valid @RequestBody ResignRequest request
    ) {
        ResignResponse response = userService.resign(request);

        return ResponseEntity.ok(
                new ApiResponse<>("200", "회원 탈퇴 성공", response)
        );
    }

    @Operation(
            summary = "회원 정보 수정",
            description = "사용자의 loginId, password, name 중 변경할 값을 입력하여 수정합니다."
    )
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UpdateUserResponse>> updateUser(
            @Valid @RequestBody UpdateUserRequest request
    ) {
        UpdateUserResponse response = userService.updateUser(request);

        return ResponseEntity.ok(
                new ApiResponse<>("200", "회원 정보 수정 성공", response)
        );
    }

    @Operation(summary = "내 프로필 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getMyProfile() {
        UserProfileResponse response = userService.getMyProfile();

        return ResponseEntity.ok(
                new ApiResponse<>("200", "내 프로필 조회 성공", response)
        );
    }

}
