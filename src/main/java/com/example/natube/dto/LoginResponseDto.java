package com.example.natube.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String username;
    private String password;

    public LoginResponseDto(String username, String password){
        this.username= username;
        this.password = password;
    }

}
