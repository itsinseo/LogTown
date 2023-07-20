package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.security.UserDetailsImpl;
import com.sarida.logtown.service.CommentService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 게시글에 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponseDto> postComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ApiResponseDto apiResponseDto = commentService.postComment(postId, requestDto, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDto);
    }

    // 댓글에 대댓글 작성
    @PostMapping("/comments/{commentId}/recomment")
    public ResponseEntity<ApiResponseDto> postReComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Comment parentComment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.postReComment(parentComment, requestDto, userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Comment comment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.putComment(comment, requestDto, userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("해당 댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        // jwt, aop 추가 후 주석 해제
//        catch (RejectedExecutionException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("댓글의 작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
//        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Comment comment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.deleteComment(comment, userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("해당 댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        // jwt, aop 추가 후 주석 해제
//        catch (RejectedExecutionException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("댓글의 작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
//        }
    }

    @PostMapping("comments/{commentId}/like")
    public ResponseEntity<ApiResponseDto> likeComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.likeComment(commentId, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("댓글에 좋아요를 남기셨습니다", HttpStatus.ACCEPTED.value()));
        } catch (DuplicateRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("comments/{commentId}/like")
    public ResponseEntity<ApiResponseDto> deleteLikeComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.deleteLikeComment(commentId, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("댓글에 있는 좋아요를 취소하셨습니다", HttpStatus.ACCEPTED.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}