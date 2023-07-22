package com.sarida.logtown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String loginSelect() {
        return "loginselect";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/mainpage") // !!임시 메인페이지
    public String mainpage() {
        return "mainpage";
    }

    @GetMapping("/detailpage") // 유저페이지
    public String detailpage() {
        return "detail-profile";
    }
}
