package com.sarida.logtown.dto;

import com.sarida.logtown.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
//	@Pattern(regexp = "^[a-z0-9]{4,10}$")
	private String username;
//	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{8,15}$")   테스트동안 글자수 패턴x
	private String password;
	private String nickname;
	private String introduction;
	private UserRoleEnum role;
}
