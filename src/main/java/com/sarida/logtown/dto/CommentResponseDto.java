package com.sarida.logtown.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sarida.logtown.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto extends ApiResponseDto {

    private final Long id;
    private final String content;
    private final String nickname;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<ReCommentResponseDto> reCommentList;
    private final Integer likeCnt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.likeCnt = comment.getCommentLikes().size();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.reCommentList = comment.getChildCommentList().stream().map(ReCommentResponseDto::new)
                .sorted(Comparator.comparing(ReCommentResponseDto::getCreatedAt).reversed())
                .toList();
    }
}
