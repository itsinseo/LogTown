package com.sarida.logtown.dto;

import com.sarida.logtown.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
	@Pattern(regexp = "^[a-z0-9]{4,10}$")
	private String username;
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{8,15}$")
	private String password;
	private String nickname;
	private String introduction;
	private UserRoleEnum role;
}
