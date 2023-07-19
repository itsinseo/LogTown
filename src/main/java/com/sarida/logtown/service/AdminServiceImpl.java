package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.CommentRepository;
import com.sarida.logtown.repository.PostRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponseDto deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );

        postRepository.delete(post);
        return new ApiResponseDto("관리자 권한 게시글 삭제", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 댓글입니다.")
        );

        commentRepository.delete(comment);
        return new ApiResponseDto("관리자 권한 댓글 삭제", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 사용자입니다.")
        );

        userRepository.delete(user);
        return new ApiResponseDto("관리자 권한 사용자 삭제", HttpStatus.OK.value());
    }

}
