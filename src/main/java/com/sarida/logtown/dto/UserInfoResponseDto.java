package com.sarida.logtown.dto;

import com.sarida.logtown.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private Long id;
    private String username;
    private String nickname;

    public UserInfoResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
