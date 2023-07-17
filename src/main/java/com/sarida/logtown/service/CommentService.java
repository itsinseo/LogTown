package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;

public interface CommentService {

    ApiResponseDto postComment(Long postId, CommentRequestDto commentRequestDto);

    ApiResponseDto putComment(Comment comment, CommentRequestDto commentRequestDto);

    ApiResponseDto deleteComment(Comment comment);

    Comment findComment(Long commentId);
}
