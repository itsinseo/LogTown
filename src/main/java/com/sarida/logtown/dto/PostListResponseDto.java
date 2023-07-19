package com.sarida.logtown.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {
    private List<PostResponseDto> postList;

    public PostListResponseDto(List<PostResponseDto> postList) {
        this.postList = postList;
    }
}
