package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PostListResponseDto;
import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.security.UserDetailsImpl;
import com.sarida.logtown.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping("")
    public PostResponseDto createPost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.createPost(requestDto, userDetails);
    }

    @GetMapping("")
    public PostListResponseDto getPostList() {
        return postService.getPostList();
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.updatePost(postId, requestDto, userDetails);
    }

    @DeleteMapping("/{postId}")
    public ApiResponseDto deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.deletePost(postId,userDetails);
    }
}
