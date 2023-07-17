package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.dto.CommentResponseDto;
import com.sarida.logtown.entity.Comment;

import java.util.List;

public interface CommentService {

    ApiResponseDto postComment(Long postId, CommentRequestDto commentRequestDto);

    ApiResponseDto postReComment(Comment parentComment, CommentRequestDto commentRequestDto);

    ApiResponseDto putComment(Comment comment, CommentRequestDto commentRequestDto);

    ApiResponseDto deleteComment(Comment comment);

    Comment findComment(Long commentId);

    // 댓글 조회 테스트용
    List<CommentResponseDto> getComments();
}
