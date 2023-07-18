package com.sarida.logtown.dto;

import com.sarida.logtown.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReCommentResponseDto {
    private final Long id;
    private final String content;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReCommentResponseDto(Comment reComment) {
        this.id = reComment.getId();
        this.content = reComment.getContent();
        this.username = reComment.getUser().getUsername();
        this.createdAt = reComment.getCreatedAt();
        this.modifiedAt = reComment.getModifiedAt();
    }
}
