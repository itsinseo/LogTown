package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public ApiResponseDto postComment(Long postId, CommentRequestDto commentRequestDto) {
        Comment comment = new Comment(commentRequestDto.getContent());

        commentRepository.save(comment);
        return new ApiResponseDto("댓글 작성 완료", HttpStatus.CREATED.value());
    }

    @Override
    @Transactional
    public ApiResponseDto putComment(Comment comment, CommentRequestDto commentRequestDto) {
        comment.setContent(commentRequestDto.getContent());
        return new ApiResponseDto("댓글 수정 완료", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deleteComment(Comment comment) {
        commentRepository.delete(comment);
        return new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK.value());
    }

    @Override
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );
    }
}
