package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.security.UserDetailsImpl;
import com.sarida.logtown.service.PostService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public PostResponseDto createPost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.createPost(requestDto, userDetails);
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/slice")
    public Slice<PostResponseDto> getPostSlice(@RequestParam("page") int page) {
        return postService.getPostSlice(page);
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
        return postService.deletePost(postId, userDetails);
    }

    @GetMapping("/myposts")
    public Slice<PostResponseDto> getMyPosts(@RequestParam("page") int page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getMyPosts(page, userDetails);
    }

    @GetMapping("/followingposts")
    public List<PostResponseDto> getFollowingPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getFollowingPosts(userDetails);
    }

    @PostMapping("/{postId}/like")
    public ApiResponseDto likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.likePost(postId, userDetails.getUser());
        } catch (DuplicateRequestException e) {
            return new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return new ApiResponseDto("게시글에 좋아요를 남기셨습니다", HttpStatus.ACCEPTED.value());
    }

    @DeleteMapping("/{postId}/like")
    public ApiResponseDto deleteLikePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.deleteLikePost(postId, userDetails.getUser());
        } catch (IllegalArgumentException e) {
            return new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return new ApiResponseDto("게시글에 있는 좋아요를 취소하셨습니다", HttpStatus.ACCEPTED.value());
    }
}
