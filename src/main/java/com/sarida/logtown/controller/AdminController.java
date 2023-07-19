package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    @DeleteMapping("/posts/{postId}/delete")
    public ApiResponseDto deletePost(@PathVariable Long postId) {
        return adminService.deletePost(postId);
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public ApiResponseDto deleteComment(@PathVariable Long commentId) {
        return adminService.deleteComment(commentId);
    }

    @DeleteMapping("/users/{userId}/closing")
    public ApiResponseDto deleteUser(@PathVariable Long userId) {
        return adminService.deleteUser(userId);
    }
}
