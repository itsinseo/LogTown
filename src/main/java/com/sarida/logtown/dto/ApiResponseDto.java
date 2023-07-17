package com.sarida.logtown.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
	private String mag;
	private Integer statusCode;

	public ApiResponseDto(String msg, Integer statusCode) {
		this.mag = msg;
		this.statusCode = statusCode;
	}
}
