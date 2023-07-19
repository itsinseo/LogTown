package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/follow")
public class FollowController {

	private final FollowService followService;

	// 팔로우
	@PostMapping("/{toUsername}/{fromUsername}")
	public ResponseEntity<?> addFollow(@PathVariable String toUsername, @PathVariable String fromUsername) {
		Boolean result = followService.addFollow(toUsername, fromUsername);

		if (result) {
			return ResponseEntity.status(200).body(new ApiResponseDto("팔로우", HttpStatus.OK.value()));
		}
		return ResponseEntity.status(400).body(new ApiResponseDto("오류", HttpStatus.BAD_REQUEST.value()));
	}

	// TODO : 언팔로우, 팔로워/팔로잉 리스트 가져오기, 팔로잉 글만 가져오기
	// 언팔로우
//	@DeleteMapping("/{toUsername}/{fromUsername}")
//	public ResponseEntity<?> unFollow(@PathVariable String toUsername, @PathVariable String fromUsername) {
//		unFollow(toUsername, fromUsername);
//	}

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
