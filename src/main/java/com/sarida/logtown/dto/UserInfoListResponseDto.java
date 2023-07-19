package com.sarida.logtown.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInfoListResponseDto {
    private List<UserInfoResponseDto> userInfoList;

    public UserInfoListResponseDto(List<UserInfoResponseDto> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
