package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;
}
