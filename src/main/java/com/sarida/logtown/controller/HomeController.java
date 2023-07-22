package com.sarida.logtown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // !!모든 경로(URL)은 임시

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
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/home/mainpageMJ") // 민지 메인페이지
    public String mainPageMJ() {
        return "mainpageMJ";
    }

    @GetMapping("/home/checkpassword")
    public String checkPassword() {
        return "checkpassword";
    }

    @GetMapping("/home/changepassword")
    public String changePassword() {
        return "changepassword";
    }
}
