package com.sarida.logtown.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageUserInfosDto {
    private Page<UserInfoResponseDto> userInfoPage;

    public PageUserInfosDto(Page<UserInfoResponseDto> userInfoPage) {
        this.userInfoPage = userInfoPage;
    }
}
