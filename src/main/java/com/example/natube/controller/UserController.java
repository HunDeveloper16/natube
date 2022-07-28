package com.example.natube.controller;


import com.example.natube.Service.UserService;
import com.example.natube.dto.LoginRequestDto;
import com.example.natube.dto.LoginResponseDto;
import com.example.natube.dto.SignupRequestDto;
import com.example.natube.dto.SignupResponseDto;
import com.example.natube.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    private final UserDetailsServiceImpl userDetailsService;


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public SignupResponseDto registerUser(@RequestBody SignupRequestDto requestDto) {

        return userService.registerUser(requestDto);
    }

    //  로그인 요청 처리
    @PostMapping("/user/login")
    public LoginResponseDto loginUser(@RequestBody LoginRequestDto requestDto) {

        return userService.login(requestDto);

    }
}



