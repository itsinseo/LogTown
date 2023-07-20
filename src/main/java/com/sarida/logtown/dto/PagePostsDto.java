package com.sarida.logtown.dto;

import com.sarida.logtown.entity.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PagePostsDto {
    private Page<PostResponseDto> postPage;

    public PagePostsDto(Page<PostResponseDto> postPage) {
        this.postPage = postPage;
    }
}
