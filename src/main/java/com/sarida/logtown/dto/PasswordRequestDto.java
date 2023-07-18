package com.sarida.logtown.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequestDto {

    String currentPassword;
    String changedPassword;
    String confirmedPassword;
}
