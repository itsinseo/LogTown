package com.sarida.logtown.service;

import com.sarida.logtown.dto.PasswordRequestDto;
import com.sarida.logtown.dto.ProfileRequestDto;
import com.sarida.logtown.entity.Password;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.PasswordRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private static final int MAX_HISTORY = 3;

    public void updateProfile(ProfileRequestDto requestDto, User user) {
        String introduction = requestDto.getIntroduction();
        String nickname = requestDto.getNickname();


        if(nickname.equals(user.getNickname())) {

        }
        else if(userRepository.findByNickname(nickname).isPresent()) {
            throw new RejectedExecutionException("중복되는 닉네임입니다.");
        } else {
            user.setNickname(nickname);
        }

        // 닉네임, 소개 수정
        user.setIntroduction(introduction);

        userRepository.save(user);
    }

    @Transactional
    public void updatePassword(PasswordRequestDto requestDto, User user) {
        String currentPassword = requestDto.getCurrentPassword();
        String changedPassword = requestDto.getChangedPassword();
        String confirmedPassword = requestDto.getConfirmedPassword();

        String encodedCurrentUserPassword = user.getPassword();

        // 현재 비밀번호 체크
        if(passwordEncoder.matches(currentPassword, encodedCurrentUserPassword)) {
            // 변경할 비밀번호하고 ccnfirm 비밀번호 체크
            if(changedPassword.equals(confirmedPassword)) {
                if(!isRecentPassword(user,changedPassword)) {
                    Password password = new Password(changedPassword,user);

                    user.setPassword(changedPassword); // 비밀변호 변경

                    user.getPasswordList().add(password);
                    if (user.getPasswordList().size() > MAX_HISTORY) {
                        Password oldestPassword = user.getPasswordList().remove(0);
                        passwordRepository.delete(oldestPassword); // 가장 오래된 비밀번호 삭제
                    }
                    // DB 변경
                    userRepository.save(user);
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

    private boolean isRecentPassword(User user, String password) {
        for( Password pw: user.getPasswordList()) {
            if(passwordEncoder.matches(password,pw.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
