package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.entity.User;
import com.sarida.logtown.security.UserDetailsImpl;

public interface CommentService {
    /**
     * 댓글 생성
     * @param postId 댓글을 생성할 게시글 id
     * @param requestDto 댓글 생성 요청 정보
     * @param userDetails 댓글 생성 요청자
     * @return 요청 처리 메세지 + 상태 코드
     */
    ApiResponseDto postComment(Long postId, CommentRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 대댓글 생성
     * @param parentComment 대댓글을 생성할 댓글
     * @param requestDto 대댓글 생성 요청 정보
     * @param userDetails 대댓글 생성 요청자
     * @return 요청 처리 메세지 + 상태 코드
     */
    ApiResponseDto postReComment(Comment parentComment, CommentRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 댓글 수정
     * @param comment 수정할 댓글
     * @param requestDto 댓글 수정 내용 정보
     * @param userDetails 댓글 수정 요청자
     * @return 요청 처리 메세지 + 상태 코드
     */
    ApiResponseDto putComment(Comment comment, CommentRequestDto requestDto, UserDetailsImpl userDetails);

    /**
     * 댓글 삭제
     * @param comment 삭제할 댓글
     * @param userDetails 댓글 삭제 요청자
     * @return 요청 처리 메세지 + 상태 코드
     */
    ApiResponseDto deleteComment(Comment comment, UserDetailsImpl userDetails);

    /**
     * 댓글 조회
     * @param commentId 조회할 댓글 id
     * @return 조회한 댓글
     */
    Comment findComment(Long commentId);

    /**
     * 댓글 좋아요
     * @param commentId 좋아요할 댓글 id
     * @param user 좋아요할 유저
     */
    void likeComment(Long commentId, User user);

    /**
     * 댓글 좋아요 삭제
     * @param commentId 좋아요 삭제할 댓글 id
     * @param user 좋아요 삭제할 유저
     */
    void deleteLikeComment(Long commentId, User user);

    /**
     * 대댓글 좋아요
     * @param recommentId 좋아요할 대댓글 id
     * @param user 좋아요할 유저
     */
    void likeRecomment(Long recommentId, User user);

    /**
     * 대댓글 좋아요 삭제
     * @param recommentId 좋아요 삭제할 대댓글 id
     * @param user 좋아요 삭제할 유저
     */
    void deleteLikeRecomment(Long recommentId, User user);
}
