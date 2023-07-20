package com.sarida.logtown.dto;

import com.sarida.logtown.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class SigninRequestDto {
	private String username;
	private String password;
	private UserRoleEnum role;
}
