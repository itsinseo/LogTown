package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PostRepository postRepository;

    @Override
    public ApiResponseDto deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );

        postRepository.delete(post);
        return new ApiResponseDto("관리자 권한 게시글 삭제", HttpStatus.OK.value());
    }
}
