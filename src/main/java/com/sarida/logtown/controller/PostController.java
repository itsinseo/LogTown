package com.sarida.logtown.controller;

import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    @PostMapping("")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(postId, requestDto);
    }
}
