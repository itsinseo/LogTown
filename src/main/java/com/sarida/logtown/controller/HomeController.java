package com.sarida.logtown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String loginSelect() {
        return "loginselect";
    }

    @GetMapping("/home/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/home/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/home/mainpage") // !!임시 메인페이지
    public String mainpage() {
        return "mainpage";
    }
}
