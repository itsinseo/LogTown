package com.sarida.logtown.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageCommentsDto {
    Page<CommentResponseDto> commentPage;

    public PageCommentsDto(Page<CommentResponseDto> commentPage) {
        this.commentPage = commentPage;
    }
}
