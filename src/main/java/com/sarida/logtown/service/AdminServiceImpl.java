package com.sarida.logtown.service;

import com.sarida.logtown.dto.*;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.repository.CommentRepository;
import com.sarida.logtown.repository.PostRepository;
import com.sarida.logtown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
    public ApiResponseDto deletePosts(SelectedIdsDto selectedIds) {
        List<Long> postIds = selectedIds.getSelectedIds();
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
    public ApiResponseDto deleteComments(SelectedIdsDto selectedIds) {
        List<Long> commentIds = selectedIds.getSelectedIds();
        for (Long id : commentIds) {
            Comment comment = commentRepository.findById(id).orElse(null);
            if (comment == null) {
                continue;
            }
            commentRepository.delete(comment);
        }

        return new ApiResponseDto("관리자 권한 댓글 여러개 삭제", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto deleteUsers(SelectedIdsDto selectedIds) {
        List<Long> userIds = selectedIds.getSelectedIds();
        for (Long id : userIds) {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                continue;
            }
            userRepository.delete(user);
        }

        return new ApiResponseDto("관리자 권한 계정 여러개 삭제", HttpStatus.OK.value());
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
    public PageCommentsDto getCommentsByPage(int page, boolean isAsc) {
        // 페이징
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Page<Comment> commentPage = commentRepository.findAll(pageable);

        return new PageCommentsDto(commentPage.map(CommentResponseDto::new));
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

    @Override
    public Slice<PostResponseDto> getSlicePosts(int page) {
        // 페이징
        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        // 전체 게시글 목록
        Slice<Post> postSlice = postRepository.findAll(pageable);

        return postSlice.map(PostResponseDto::new);
    }
}
