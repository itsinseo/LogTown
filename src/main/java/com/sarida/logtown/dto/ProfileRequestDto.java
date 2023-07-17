package com.sarida.logtown.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {

    String nickname;
    String introduction;
    String password;
    String confirmPassword;
}
