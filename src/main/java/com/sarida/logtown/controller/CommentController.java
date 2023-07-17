package com.sarida.logtown.controller;

import com.sarida.logtown.dto.ApiResponseDto;
import com.sarida.logtown.dto.CommentRequestDto;
import com.sarida.logtown.entity.Comment;
import com.sarida.logtown.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponseDto> postComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        ApiResponseDto apiResponseDto = commentService.postComment(postId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDto);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        try {
            Comment comment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.putComment(comment, commentRequestDto);
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
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId) {
        try {
            Comment comment = commentService.findComment(commentId);
            ApiResponseDto apiResponseDto = commentService.deleteComment(comment);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("해당 댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        // jwt, aop 추가 후 주석 해제
//        catch (RejectedExecutionException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto("댓글의 작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
//        }
    }
}
