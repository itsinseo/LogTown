package com.sarida.logtown.controller;

import com.sarida.logtown.dto.*;
import com.sarida.logtown.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    // 게시글 관리자 삭제
    @DeleteMapping("/posts/{postId}/delete")
    public ApiResponseDto deletePost(@PathVariable Long postId) {
        return adminService.deletePost(postId);
    }

    // 게시글 여러개 삭제
    @PostMapping("/posts/delete")
    public ApiResponseDto deletePosts(@RequestBody SelectPostDto selectPostDto) {
        return adminService.deletePosts(selectPostDto);
    }

    // 댓글 관리자 삭제
    @DeleteMapping("/comments/{commentId}/delete")
    public ApiResponseDto deleteComment(@PathVariable Long commentId) {
        return adminService.deleteComment(commentId);
    }

    // user 관리자 삭제
    @DeleteMapping("/users/{userId}/closing")
    public ApiResponseDto deleteUser(@PathVariable Long userId) {
        return adminService.deleteUser(userId);
    }

    // 글 전체 보기
    @GetMapping("/posts")
    public PostListResponseDto getAllPosts() {
        return adminService.getAllPosts();
    }

    // 글 전체 보기 (Page)
    @GetMapping("/posts/paging")
    public PagePostsDto getPostsByPage(@RequestParam("page") int page, @RequestParam("isAsc") boolean isAsc) {
        return adminService.getPostsByPage(page, isAsc);
    }

    // 사용자 전체 보기
    @GetMapping("/users")
    public UserInfoListResponseDto getAllUserInfos() {
        return adminService.getAllUserInfos();
    }

    // 댓글 전체 보기
    @GetMapping("/comments")
    public CommentListResponseDto getAllComments() {
        return adminService.getAllComments();
    }

}
