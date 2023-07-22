package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PasswordRequestDto;
import com.sarida.logtown.dto.ProfileRequestDto;
import com.sarida.logtown.security.UserDetailsImpl;
import com.sarida.logtown.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PutMapping("/profiles")
    public ResponseEntity<ApiResponseDto> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProfileRequestDto requestDto) {
        try {
            profileService.updateProfile(requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("프로필 수정 완료", HttpStatus.CREATED.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponseDto> updatePassword(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PasswordRequestDto requestDto) {
        try {
            profileService.updatePassword(requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("비밀번호 수정 완료", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}