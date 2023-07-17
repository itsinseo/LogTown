package com.sarida.logtown.service;

import com.sarida.logtown.dto.SigninRequestDto;
import com.sarida.logtown.dto.SignupRequestDto;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.entity.UserRoleEnum;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void signUp(SignupRequestDto requestDto) {
		String username = requestDto.getUsername();
		String password = passwordEncoder.encode(requestDto.getPassword());
		requestDto.setPassword(password);
		String nickname = requestDto.getNickname();
		String introduction = requestDto.getIntroduction();
		UserRoleEnum role = requestDto.getRole();

		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("중복된 아이디 입니다.");
		}

		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new IllegalArgumentException("중복된 닉네임 입니다.");
		}

		User user = new User(username, password, nickname, introduction, role);
		userRepository.save(user);
	}

	public void signIn(SigninRequestDto requestDto) {
		String username = requestDto.getUsername();
		String password = requestDto.getPassword();

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 사용자 입니다.")
		);

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
	}
}
