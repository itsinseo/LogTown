package com.sarida.logtown.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponseDto {
    private List<CommentResponseDto> commentList;

    public CommentListResponseDto(List<CommentResponseDto> commentList) {
        this.commentList = commentList;
    }
}
