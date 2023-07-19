package com.sarida.logtown.service;

import com.sarida.logtown.dto.ApiResponseDto;

public interface AdminService {
    /**
     * 관리자 권한 게시글 삭제
     *
     * @param postId 삭제할 게시글
     * @return 완료 응답
     */
    ApiResponseDto deletePost(Long postId);
}
