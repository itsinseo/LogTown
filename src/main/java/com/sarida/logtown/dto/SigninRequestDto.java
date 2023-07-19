package com.sarida.logtown.dto;

import com.sarida.logtown.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequestDto {
	private String username;
	private String password;
	private UserRoleEnum role;
}
