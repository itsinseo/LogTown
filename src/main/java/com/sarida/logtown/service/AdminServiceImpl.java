package com.sarida.logtown.service;

import com.sarida.logtown.dto.*;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.CommentRepository;
import com.sarida.logtown.repository.PostRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponseDto deletePost(Long postId) {
        Post post = findPost(postId);

        postRepository.delete(post);
        return new ApiResponseDto("관리자 권한 게시글 삭제", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deletePosts(SelectPostDto selectPostDto) {
        List<Long> postIds = selectPostDto.getPostIds();
        for (Long id : postIds) {
            Post post = postRepository.findById(id).orElse(null);
            if (post == null) {
                continue;
            }
            postRepository.delete(post);
        }

        return new ApiResponseDto("관리자 권한 게시글 여러개 삭제", HttpStatus.OK.value());
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

    @Override
    public PostListResponseDto getAllPosts() {
        List<PostResponseDto> postList = postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
        return new PostListResponseDto(postList);
    }

    @Override
    public CommentListResponseDto getAllComments() {
        List<CommentResponseDto> commentList = commentRepository.findAllByOrderByModifiedAtDesc().stream().map(CommentResponseDto::new).toList();
        return new CommentListResponseDto(commentList);
    }

    @Override
    public UserInfoListResponseDto getAllUserInfos() {
        List<UserInfoResponseDto> userInfoList = userRepository.findAllByOrderByUsername().stream().map(UserInfoResponseDto::new).toList();
        return new UserInfoListResponseDto(userInfoList);
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
}
