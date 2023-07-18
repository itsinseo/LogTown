package com.sarida.logtown.service;

import com.sarida.logtown.dto.PasswordRequestDto;
import com.sarida.logtown.dto.ProfileRequestDto;
import com.sarida.logtown.entity.Password;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateProfile(User user, ProfileRequestDto requestDto) {
        String introduction = requestDto.getIntroduction();
        String nickname = requestDto.getNickname();

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new RejectedExecutionException("중복되는 닉네임입니다.");
        }

        // 닉네임, 소개 수정
        user.setIntroduction(introduction);
        user.setNickname(nickname);
    }


    @Transactional
    public void updatePassword(PasswordRequestDto requestDto, User user) {
        String currentPassword = requestDto.getCurrentPassword();
        String changedPassword = requestDto.getChangedPassword();
        String confirmedPassword = requestDto.getConfirmedPassword();

        // 현재 비밀번호 체크
        if(passwordEncoder.matches(currentPassword, user.getPassword())) {
            // 변경할 비밀번호하고 ccnfirm 비밀번호 체크
            if(changedPassword.equals(confirmedPassword)) {
                Password password = new Password(changedPassword);
                if(!user.isRecentPassword(password)) {
                    user.addPassword(password);
                }
                else {
                    throw new RejectedExecutionException("최근 3번이내에 사용한 비밀번호 입니다.");
                }
            }
            else {
                throw new RejectedExecutionException("비밀번호가 일치하지 않습니다.");
            }
        }
        else {
            throw new RejectedExecutionException("현재 비밀번호가 일치하지 않습니다.");
        }
    }
}
