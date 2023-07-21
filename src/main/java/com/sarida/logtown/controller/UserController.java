package com.sarida.logtown.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.SigninRequestDto;
import com.sarida.logtown.dto.SignupRequestDto;
import com.sarida.logtown.jwt.JwtUtil;
import com.sarida.logtown.service.KakaoService;
import com.sarida.logtown.service.NaverService;
import com.sarida.logtown.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final KakaoService kakaoService;
    private final NaverService naverService;

    @ResponseBody
    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponseDto> signUp(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("아이디/비밀번호의 구성이 올바르지 않습니다.");
        }

        try {
            userService.signUp(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @ResponseBody
    @PostMapping("/auth/signin")
    public ResponseEntity<ApiResponseDto> signIn(@RequestBody SigninRequestDto requestDto, HttpServletResponse response) {
        try {
            userService.signIn(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getUsername(), requestDto.getRole()));
        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String token = kakaoService.kakaoLogin(code);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/home/mainpage";
    }

    @GetMapping("/auth/naver/callback")
    public String naverLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        String token = naverService.naverLogin(code);

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/home/mainpage";
    }
}
