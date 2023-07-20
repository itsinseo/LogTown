package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.exception.RestApiException;
import com.sarida.logtown.security.UserDetailsImpl;
import com.sarida.logtown.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/follow")
public class FollowController {

	private final FollowService followService;

	// 팔로우
	@PostMapping("/{toUsername}")
	public ResponseEntity<?> addFollow(@PathVariable String toUsername, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			ApiResponseDto result = followService.addFollow(toUsername, userDetails.getUser());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	// TODO : 팔로워/팔로잉 리스트 가져오기, 팔로잉 글만 가져오기

	//	 언팔로우
	@DeleteMapping("/{toUsername}")
	public ResponseEntity<?> unFollow(@PathVariable String toUsername, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			ApiResponseDto result = followService.unFollow(toUsername, userDetails.getUser());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	// 내가 팔로우 하는 User List 가져오기
//	@GetMapping("/{username}/following")
//	public ResponseEntity<ApiResponseDto> getFollowingList(@PathVariable String username) {
//		try {
//			followService.getFollowingList(username);
//		}
//	}

	// 나를 팔로우 하는 User List 가져오기
//	@GetMapping("/{username}/followers")
//	public ResponseEntity<ApiResponseDto> getFollowerList(@PathVariable String username) {
//
//	}
}
