package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.entity.Follow;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.FollowRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
	Logger logger = LoggerFactory.getLogger(FollowService.class);

	private final UserRepository userRepository;
	private final FollowRepository followRepository;

	public ApiResponseDto addFollow(String toUsername, User fromUser) {
		logger.info("addFollow 메서드 진입");
		Optional<Follow> relation = checkUserInfo(toUsername, fromUser);

		if (relation.isPresent()) {
			throw new IllegalArgumentException("현재 팔로우 중입니다.");
		}
		logger.info("followRepository.save 전");
		followRepository.save(new Follow(toUsername, fromUser.getUsername()));
		logger.info("followRepository.save 후");
		return new ApiResponseDto("팔로우", 200);
	}

	public ApiResponseDto unFollow(String toUsername, User fromUser) {
		logger.info("unFollow 메서드 진입");
		Optional<Follow> relation = checkUserInfo(toUsername, fromUser);

		if (relation.isEmpty()) {
			throw new IllegalArgumentException("현재 팔로우 중이 아닙니다.");
		}
		logger.info("followRepository.delete 전");
		followRepository.delete(relation.get());
		logger.info("followRepository.delete 후");
		return new ApiResponseDto("팔로우 취소", 200);
	}

	// 팔로우할 유저가 존재하는지, 스스로를 팔로우 하는지 검증
	public Optional<Follow> checkUserInfo(String toUsername, User fromUser) {
		logger.info("checkUserInfo 메서드 진입");
		if (toUsername.equals(fromUser.getUsername())) {
			throw new IllegalArgumentException("스스로는 팔로우 할 수 없습니다.");
		}

		User toUser = userRepository.findByUsername(toUsername).orElseThrow(
				() -> new UsernameNotFoundException("존재하지 않는 사용자입니다.")
		);
		logger.info("toUser 가져오기 : " + toUser.getUsername());

		Optional<Follow> relation = getFollowRelation(toUser.getUsername(), fromUser.getUsername());
		logger.info("relation findByToUserAndFromUser : " + relation);

		return relation;
	}

	// 이미 팔로잉/팔로우 중인 관계가 있는지
	private Optional<Follow> getFollowRelation(String toUsername, String fromUsername) {
		logger.info("getFollowRelation 메서드 진입");
		return followRepository.findByToUserAndFromUser(toUsername, fromUsername);
	}
}

//	public List<FollowingListDto> getFollowingList(String username) {
//		User user = userRepository.findByUsername(username).orElseThrow(
//				() -> new UsernameNotFoundException()
//		);
//
//		List<Follow> followingList = followRepository.findAllByFromUser(user);
//
//		List<FollowingListDto> followingListDto = new ArrayList<>();
//		for (Follow follow : followingList) {
//			followingListDto.add(
//					new FollowingListDto(follow.getToUser().getUsername())
//			)
//		}
//	}
//
//	public List<FollowingListDto> getFollowingList(String username) {
//
//	}
//}
