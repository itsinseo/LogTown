package com.sarida.logtown.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarida.logtown.dto.NaverUserInfoDto;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.entity.UserRoleEnum;
import com.sarida.logtown.jwt.JwtUtil;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j(topic = "네이버 로그인")
@Service
@RequiredArgsConstructor
public class NaverService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    public String naverLogin(String code) throws JsonProcessingException, UnsupportedEncodingException {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getToken(code);

        // 2. 토큰으로 네이버 API 호출 : "액세스 토큰"으로 "네이버 사용자 정보" 가져오기
        NaverUserInfoDto naverUserInfo = getNaverUserInfo(accessToken);

        // 3. 필요시에 회원가입
        User naverUser = registerNaverUserIfNeeded(naverUserInfo);

        // 4. JWT 토큰 반환
        String createToken = jwtUtil.createToken(naverUser.getUsername(), naverUser.getRole());

        return createToken;
    }

    private String getToken(String code) throws JsonProcessingException, UnsupportedEncodingException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://nid.naver.com")
                .path("/oauth2.0/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", naverClientId)
                .queryParam("client_secret", naverClientSecret)
                .queryParam("code", code)
                .queryParam("state", URLEncoder.encode("1234", "UTF-8")) //state: 임의 값 1234로 설정
                .encode()
                .build()
                .toUri();

        StringBuilder responseBuilder = new StringBuilder();

        try {
            URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = responseBuilder.toString();

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response);
        return jsonNode.get("access_token").asText();
    }

    private NaverUserInfoDto getNaverUserInfo(String accessToken) throws JsonProcessingException {
        String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL, requestHeaders);

        JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
        String id = jsonNode.get("response").get("id").asText();
        String nickname = jsonNode.get("response").get("nickname").asText();

        return new NaverUserInfoDto(id, nickname);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private User registerNaverUserIfNeeded(NaverUserInfoDto naverUserInfo) {
        // Naver Username 중복 확인
        String naverUsername = naverUserInfo.getUsername();
        User naverUser = userRepository.findByUsername(naverUsername).orElse(null);

        if (naverUser == null) {
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);

            // nickname의 경우 중복 방지를 위해 무작위 UUID 추가 -> 프론트에서 프로필 재설정 필요 메세지 띄우기
            String uniqueNickname = naverUserInfo.getNickname() + "@Naver" + UUID.randomUUID();

            naverUser = new User(naverUsername, encodedPassword, uniqueNickname,
                    "소개 문구를 추가해주세요.", UserRoleEnum.USER);

            userRepository.save(naverUser);
        }

        return naverUser;
    }
}
