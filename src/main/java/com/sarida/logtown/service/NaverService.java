package com.sarida.logtown.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.entity.UserRoleEnum;
import com.sarida.logtown.jwt.JwtUtil;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NaverService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public String naverLogin(String code) throws JsonProcessingException {

//        // 1. "인가 코드"로 "액세스 토큰" 요청
//        String accessToken = getToken(code);

//        // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
//        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
//
//        // 3. 필요시에 회원가입
//        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        User naverUser = new User("naverusername", passwordEncoder.encode("password"), "네이버닉네임", "네이버안녕하세요", UserRoleEnum.USER);

        // 4. JWT 토큰 반환
        String createToken = jwtUtil.createToken(naverUser.getUsername());

        return createToken;
    }
}
