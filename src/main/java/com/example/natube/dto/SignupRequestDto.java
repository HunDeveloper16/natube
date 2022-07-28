package com.example.natube.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String nickname;

    public SignupRequestDto(String username, String password, String passwordCheck, String nickname){
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
    }
}
