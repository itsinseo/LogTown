package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentListResponseDto;
import com.sarida.logtown.dto.PostListResponseDto;
import com.sarida.logtown.dto.UserInfoListResponseDto;

import java.util.List;

public interface AdminService {
    /**
     * 관리자 권한 게시글 삭제
     *
     * @param postId 삭제할 게시글 id
     * @return 완료 응답
     */
    ApiResponseDto deletePost(Long postId);

    /**
     * 관리자 권한 댓글 삭제
     *
     * @param commentId 삭제할 댓글 id
     * @return 완료 응답
     */
    ApiResponseDto deleteComment(Long commentId);

    /**
     * 관리자 권한 user 삭제
     *
     * @param userId 삭제할 user id
     * @return 완료 응답
     */
    ApiResponseDto deleteUser(Long userId);

    /**
     * 모든 사용자 조회
     *
     * @return 사용자 dto list
     */
    UserInfoListResponseDto getAllUserInfos();

    /**
     * 게시글 전체 보기
     *
     * @return 전체 게시글 list
     */
    PostListResponseDto getAllPosts();

    /**
     * 댓글 전체 보기
     *
     * @return 전체 댓글 list
     */
    CommentListResponseDto getAllComments();
}
