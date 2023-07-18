package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.repository.CommentRepository;
import com.sarida.logtown.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Override
    public ApiResponseDto postComment(Long postId, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = postService.findPost(postId);
        Comment comment = new Comment(requestDto.getContent());
        comment.setUser(userDetails.getUser());
        comment.setPost(post);
        commentRepository.save(comment);
        return new ApiResponseDto("댓글 작성 완료", HttpStatus.CREATED.value());
    }

    @Override
    public ApiResponseDto postReComment(Comment parentComment, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto.getContent());
        comment.setUser(userDetails.getUser());
        comment.setParentComment(parentComment);
        commentRepository.save(comment);
        return new ApiResponseDto("대댓글 작성 완료", HttpStatus.CREATED.value());
    }

    @Override
    @Transactional
    public ApiResponseDto putComment(Comment comment, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        // 댓글 작성자 확인
        if (comment.getUser().equals(userDetails.getUser())) {
            comment.setContent(requestDto.getContent());
        } else {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }

        return new ApiResponseDto("댓글 수정 완료", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deleteComment(Comment comment, UserDetailsImpl userDetails) {
        // 댓글 작성자 확인
        if (comment.getUser().equals(userDetails.getUser())) {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }

        return new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK.value());
    }

    @Override
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );
    }
}
