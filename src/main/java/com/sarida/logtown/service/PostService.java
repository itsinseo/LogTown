package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PostListResponseDto;
import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.security.UserDetailsImpl;

import java.util.List;

public interface PostService {
    /**
     * 게시글 생성
     * @param requestDto 게시글 생성 요청 정보
     * @param userDetails 게시글 생성 요청자
     * @return 생성된 게시글 정보
     */
    PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 게시글 수정
     * @param postId 수정할 게시글 id
     * @param requestDto 게시글 수정 내용 정보
     * @param userDetails 게시글 수정 요청자
     * @return 수정된 게시글 정보
     */
    PostResponseDto updatePost(Long postId, PostRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 전체 게시글 조회
     * @return 전체 게시글 List
     */
    PostListResponseDto getPostList();

    /**
     * 게시글 삭제
     * @param postId 삭제할 게시글 id
     * @param userDetails 게시글 삭제 요청자
     * @return 완료 응답
     */
    ApiResponseDto deletePost(Long postId, UserDetailsImpl userDetails);

    /**
     * 게시글 조회
     * @param postId 조회할 게시글 id
     * @return 조회한 게시글
     */
    Post findPost(Long postId);
}
