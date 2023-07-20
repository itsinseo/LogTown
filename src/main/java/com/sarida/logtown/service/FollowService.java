package com.sarida.logtown.service;

import com.sarida.logtown.entity.Follow;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.FollowRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
	Logger logger = LoggerFactory.getLogger(FollowService.class);

	private final UserRepository userRepository;
	private final FollowRepository followRepository;

	public Boolean addFollow(String toUsername, User fromUser) {
		logger.info("addFollow 메서드 진입");
		if (toUsername.equals(fromUser.getUsername())) {
			throw new IllegalArgumentException();
		}

		User toUser = userRepository.findByUsername(toUsername).orElseThrow(IllegalArgumentException::new);
		logger.info("toUser 가져오기 : " + toUser.getUsername());

		Optional<Follow> relation = getFollowRelation(toUser.getUsername(), fromUser.getUsername());
		logger.info("relation findByToUserAndFromUser : " + relation);
		if (relation.isPresent()) {
			throw new IllegalArgumentException();
		}
		logger.info("followRepository.save 전");
		followRepository.save(new Follow(toUser.getUsername(), fromUser.getUsername()));
		logger.info("followRepository.save 후");
		return true;
	}

	public Boolean unFollow(String toUsername, User fromUser) {
		logger.info("unFollow 메서드 진입");
		if (toUsername.equals(fromUser.getUsername())) {
			throw new IllegalArgumentException();
		}

		User toUser = userRepository.findByUsername(toUsername).orElseThrow(IllegalArgumentException::new);
		logger.info("toUser 가져오기 : " + toUser.getUsername());

		Optional<Follow> relation = getFollowRelation(toUser.getUsername(), fromUser.getUsername());
		logger.info("relation findByToUserAndFromUser : " + relation);
		if (relation.isEmpty()) {
			throw new IllegalArgumentException();
		}
		logger.info("followRepository.delete 전");
		followRepository.delete(relation.get());
		logger.info("followRepository.delete 후");
		return true;
	}

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
