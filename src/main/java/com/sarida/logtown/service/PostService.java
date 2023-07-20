package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.PostRequestDto;
import com.sarida.logtown.dto.PostResponseDto;
import com.sarida.logtown.entity.Post;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.security.UserDetailsImpl;
import org.springframework.data.domain.Slice;


public interface PostService {
    /**
     * 게시글 생성
     *
     * @param requestDto  게시글 생성 요청 정보
     * @param userDetails 게시글 생성 요청자
     * @return 생성된 게시글 정보
     */
    PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 게시글 1개 보기
     *
     * @param postId 볼 게시글 id
     * @return 게시글 내용
     */
    PostResponseDto getPost(Long postId);

    /**
     * 10개 단위로 끊어서 보기
     *
     * @param page 현재 페이지
     * @return 게시글 10개 slice
     */
    Slice<PostResponseDto> getPostSlice(int page);

    /**
     * 게시글 수정
     *
     * @param postId      수정할 게시글 id
     * @param requestDto  게시글 수정 내용 정보
     * @param userDetails 게시글 수정 요청자
     * @return 수정된 게시글 정보
     */
    PostResponseDto updatePost(Long postId, PostRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 게시글 삭제
     *
     * @param postId      삭제할 게시글 id
     * @param userDetails 게시글 삭제 요청자
     * @return 완료 응답
     */
    ApiResponseDto deletePost(Long postId, UserDetailsImpl userDetails);

    /**
     * 게시글 조회
     *
     * @param postId 조회할 게시글 id
     * @return 조회한 게시글
     */
    Post findPost(Long postId);

    /**
     * 내가 쓴 게시글 보기
     *
     * @param page        페이지 번호
     * @param userDetails 로그인된 user
     * @return 내가 쓴 게시글 10개씩 보기
     */
    Slice<PostResponseDto> getMyPosts(int page, UserDetailsImpl userDetails);

    /**
     * 좋아요 취소
     * @param postId 조회할 게시글 id
     * @param user 게시글 좋아요 취소를 할 유저
     */
    void deleteLikePost(Long postId, User user);

    /**
     * 좋아요 남기기
     * @param postId 조회할 게시글 id
     * @param user 게시글 좋아요할 유저
     */
    void likePost(Long postId, User user);
}
