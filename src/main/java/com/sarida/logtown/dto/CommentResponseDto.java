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
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<ReCommentResponseDto> commentList;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.commentList = comment.getChildCommentList().stream().map(ReCommentResponseDto::new)
                .sorted(Comparator.comparing(ReCommentResponseDto::getCreatedAt).reversed())
                .toList();
    }
}
