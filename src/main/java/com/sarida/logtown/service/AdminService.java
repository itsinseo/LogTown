package com.sarida.logtown.service;

import com.sarida.logtown.dto.*;

public interface AdminService {

    /**
     * 게시글 여러개 삭제하기
     *
     * @param selectedIds 선택된 게시글 id들
     * @return 완료 응답
     */
    ApiResponseDto deletePosts(SelectedIdsDto selectedIds);

    /**
     * 댓글 여러개 삭제
     *
     * @param selectedIds 선택된 댓글 id들
     * @return 완료 응답
     */
    ApiResponseDto deleteComments(SelectedIdsDto selectedIds);

    /**
     * user 여러개 삭제
     *
     * @param selectedIds 선택된 user id들
     * @return 완료 응답
     */
    ApiResponseDto deleteUsers(SelectedIdsDto selectedIds);

    /**
     * 게시글 전체 보기 (Page)
     *
     * @param page  페이지 번호
     * @param isAsc 오름차순인가?
     * @return 페이지
     */
    PagePostsDto getPostsByPage(int page, boolean isAsc);

    /**
     * 사용자 전체 보기 (Page)
     *
     * @param page  페이지 번호
     * @param isAsc 오름차순인가?
     * @return 페이지
     */
    PageUserInfosDto getUserInfosByPage(int page, boolean isAsc);

    /**
     * 댓글 전체 보기 (Page)
     *
     * @param page  페이지 번호
     * @param isAsc 오름차순인가?
     * @return 페이지
     */
    PageCommentsDto getCommentsByPage(int page, boolean isAsc);

}
