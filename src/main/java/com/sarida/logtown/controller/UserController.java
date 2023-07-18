package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.SigninRequestDto;
import com.sarida.logtown.dto.SignupRequestDto;
import com.sarida.logtown.jwt.JwtUtil;
import com.sarida.logtown.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

	@PostMapping("/auth/signup")
	public ResponseEntity<ApiResponseDto> signUp(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new IllegalArgumentException("아이디/비밀번호의 구성이 올바르지 않습니다.");
		}

		try {
			userService.signUp(requestDto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new ApiResponseDto("중복된 아이디 입니다.", HttpStatus.BAD_REQUEST.value()));
		}

		return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
	}

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
}
