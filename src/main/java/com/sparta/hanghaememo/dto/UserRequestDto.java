package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotNull(message = "아이디는 필수 값입니다.")
    @Size(min = 4, max = 10, message = "아이디는 4자 이상 10자 이하로 입력해야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 소문자와 숫자로만 이루어져야 합니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_+=<>?]).{8,15}$",
            message = "비밀번호는 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String password;

    private UserRole role;
}