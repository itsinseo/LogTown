package com.sarida.logtown.dto;

import com.sarida.logtown.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String nickname;
    private String introduction;
    private Integer postsCnt;

    private Boolean isMaster;

    public UserResponseDto(User user, boolean isMaster) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.introduction = user.getIntroduction();
        this.postsCnt = user.getMyPostList().size();
        this.isMaster = isMaster;
    }
}
