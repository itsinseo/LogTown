package com.sarida.logtown.service;

import com.sarida.logtown.dto.*;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.CommentRepository;
import com.sarida.logtown.repository.PostRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private static final int PAGE_SIZE = 10;

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
    public PagePostsDto getPostsByPage(int page, boolean isAsc) {
        // 페이징
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Page<Post> postPage = postRepository.findAll(pageable);

        return new PagePostsDto(postPage.map(PostResponseDto::new));
    }

    @Override
    public CommentListResponseDto getAllComments() {
        List<CommentResponseDto> commentList = commentRepository.findAllByOrderByModifiedAtDesc().stream().map(CommentResponseDto::new).toList();
        return new CommentListResponseDto(commentList);
    }

    @Override
    public PageCommentsDto getCommentsByPage(int page, boolean isAsc) {
        // 페이징
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Page<Comment> commentPage = commentRepository.findAll(pageable);

        return new PageCommentsDto(commentPage.map(CommentResponseDto::new));
    }

    @Override
    public UserInfoListResponseDto getAllUserInfos() {
        List<UserInfoResponseDto> userInfoList = userRepository.findAllByOrderByUsername().stream().map(UserInfoResponseDto::new).toList();
        return new UserInfoListResponseDto(userInfoList);
    }

    @Override
    public PageUserInfosDto getUserInfosByPage(int page, boolean isAsc) {
        // 페이징
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "username");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Page<User> userInfoPage = userRepository.findAll(pageable);

        return new PageUserInfosDto(userInfoPage.map(UserInfoResponseDto::new));
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
}
