package com.sarida.logtown.service;

import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public PostResponseDto createPost(PostRequestDto requestDto) {

        Post post = postRepository.save(new Post(requestDto));

        return new PostResponseDto(post);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);
    }
}
