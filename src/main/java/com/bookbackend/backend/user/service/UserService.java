package com.bookbackend.backend.user.service;

import com.bookbackend.backend.config.JWTProvider;
import com.bookbackend.backend.global.exception.CustomException;
import com.bookbackend.backend.global.exception.ErrorCode;
import com.bookbackend.backend.user.dto.*;
import com.bookbackend.backend.user.entity.User;
import com.bookbackend.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public JWTResponse signup(SignUpRequest request) {

        // 2. 중복 아이디 체크
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new CustomException(ErrorCode.DUPLICATE_LOGIN_ID);
        }

        // 3. 비밀번호 암호화
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // 4. 엔티티 생성
        User user = User.builder()
                .loginId(request.getLoginId())
                .password(encodedPw)
                .name(request.getName())
                .build();

        User saved = userRepository.save(user);

        // 5. JWT 발급
        String access = jwtProvider.generateAccessToken(saved.getLoginId());
        String refresh = jwtProvider.generateRefreshToken(saved.getLoginId());

        return new JWTResponse(
                access,
                refresh,
                jwtProvider.getAccessTokenExpiry()
        );
    }

    public JWTResponse login(LoginRequest request) {

        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 일치 검사
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // JWT 발급
        String access = jwtProvider.generateAccessToken(user.getLoginId());
        String refresh = jwtProvider.generateRefreshToken(user.getLoginId());

        return new JWTResponse(
                access,
                refresh,
                jwtProvider.getAccessTokenExpiry()
        );
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // loginId 변경
        if (isBlank(request.getLoginId())) {

            // 중복 아이디 체크 (자기 자신 제외)
            if (userRepository.existsByLoginId(request.getLoginId()) && !request.getLoginId().equals(user.getLoginId())) {
                throw new CustomException(ErrorCode.DUPLICATE_LOGIN_ID);
            }

            user.setLoginId(request.getLoginId());
        }

        // 비밀번호 변경
        if (isBlank(request.getPassword())) {
            String encodedPw = passwordEncoder.encode(request.getPassword());
            user.setPassword(encodedPw);
        }

        // 이름 변경
        if (isBlank(request.getName())) {
            user.setName(request.getName());
        }

        User updated = userRepository.save(user);

        return new UpdateUserResponse(
                updated.getUserId(),
                updated.getLoginId(),
                updated.getName()
        );
    }

    public ResignResponse resign(ResignRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 탈퇴 시 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        userRepository.delete(user);

        return new ResignResponse("회원 탈퇴 완료");
    }

    public UserProfileResponse getMyProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        String loginId = authentication.getPrincipal().toString();

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserProfileResponse(
                user.getUserId(),
                user.getLoginId(),
                user.getName()
        );
    }

    // ------------------------------
    // 🔹 내부 공용 유틸 함수
    // ------------------------------
    private boolean isBlank(String value) {
        return value == null && !value.trim().isEmpty();
    }
}
