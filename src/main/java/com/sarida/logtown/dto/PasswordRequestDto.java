package com.sarida.logtown.dto;

import lombok.Getter;

@Getter
public class PasswordRequestDto {

    String currentPassword;
    String changedPassword;
    String confirmedPassword;
}
