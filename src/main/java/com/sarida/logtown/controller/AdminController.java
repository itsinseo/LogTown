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

    // 게시글 여러개 삭제
    @DeleteMapping("/posts/delete")
    public ApiResponseDto deletePosts(@RequestBody SelectedIdsDto selectedIds) {
        return adminService.deletePosts(selectedIds);
    }

    // 댓글 여러개 삭제
    @DeleteMapping("/comments/delete")
    public ApiResponseDto deleteComments(@RequestBody SelectedIdsDto selectedIds) {
        return adminService.deleteComments(selectedIds);
    }

    // user 여러개 삭제
    @DeleteMapping("/users/delete")
    public ApiResponseDto deleteUsers(@RequestBody SelectedIdsDto selectedIds) {
        return adminService.deleteUsers(selectedIds);
    }

    // 글 전체 보기 (Page)
    @GetMapping("/posts")
    public PagePostsDto getPostsByPage(
            @RequestParam("page") int page,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return adminService.getPostsByPage(page, isAsc);
    }

    // 댓글 전체 보기 (Page)
    @GetMapping("/comments")
    public PageCommentsDto getCommentsByPage(
            @RequestParam("page") int page,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return adminService.getCommentsByPage(page, isAsc);
    }

    // 사용자 전체 보기 (Page)
    @GetMapping("/users")
    public PageUserInfosDto getUserInfosByPage(
            @RequestParam("page") int page,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return adminService.getUserInfosByPage(page, isAsc);
    }

}
