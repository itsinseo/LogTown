package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.repository.PostRepository;
import com.sarida.logtown.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private static final int PAGE_SIZE = 10;

    // 게시글 생성
    @Override
    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {

        Post post = postRepository.save(new Post(requestDto, userDetails.getUser()));

        return new PostResponseDto(post);
    }

    @Override
    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);
        return new PostResponseDto(post);
    }

    @Override
    public Slice<PostResponseDto> getPostSlice(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Slice<Post> postSlice = postRepository.findAllBy(pageable);

        return postSlice.map(PostResponseDto::new);
    }

    //게시글 수정
    @Override
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        // 게시글 존재 여부 확인
        Post post = findPost(postId);

        // 작성자 확인
        if (post.getUser().getId().equals(userDetails.getUser().getId())) {
            post.setContent(requestDto.getContent());
        } else {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }

        return new PostResponseDto(post);
    }

    // 게시글 삭제
    @Override
    public ApiResponseDto deletePost(Long postId, UserDetailsImpl userDetails) {
        // 게시글 존재 여부 확인
        Post post = findPost(postId);

        // 작성자 확인
        if (post.getUser().getId().equals(userDetails.getUser().getId())) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }

        return new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK.value());
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
}
