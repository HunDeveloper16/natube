package com.example.natube.controller;

import com.example.natube.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("nickname", userDetails.getNickname());
        }

        return "index";
    }
}