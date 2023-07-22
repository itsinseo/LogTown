package com.sarida.logtown.dto;

import com.sarida.logtown.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;
    private String nickname;
    private String introduction;
//    private Integer followersCnt;
//    private Integer followingCnt;
    private Integer postsCnt;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.introduction = user.getIntroduction();
        this.postsCnt = user.getMyPostList().size();
    }
}
