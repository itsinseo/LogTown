package com.sarida.logtown.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponseDto {

    private String message;
    private Integer statusCode;

    public ApiResponseDto(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
