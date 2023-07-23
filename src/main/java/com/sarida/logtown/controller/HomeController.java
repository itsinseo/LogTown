package com.sarida.logtown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/home/mainpage") // !!임시 테스트페이지
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/home/testpage") // !!임시 테스트페이지 2
    public String testPage() {
        return "testpage";
    }

    @GetMapping("/home/backoffice")
    public String backoffice() {
        return "backoffice";
    }

    @GetMapping("/home/mainpageMJ") // 민지 메인페이지
    public String mainPageMJ() {
        return "mainpageMJ";
    }

    @GetMapping("/home/all-users-mainpage") // 민지 메인페이지
    public String allUsersMainPage() {
        return "all-users-mainpage";
    }

    @GetMapping("/home/other-detail-profile") // 민지 메인페이지
    public String otherDetailProfile() {
        return "other-detail-profile";
    }

    @GetMapping("/home/onepost/{postId}")
    public String getOnePost(@PathVariable Long postId, Model model) {
        model.addAttribute("postId", postId);

        return "onepost";
    }

    @GetMapping("/home/checkpassword")
    public String checkPassword() {
        return "checkpassword";
    }

    @GetMapping("/home/changepassword")
    public String changePassword() {
        return "changepassword";
    }

    @GetMapping("/home/detailpage")
    public String detailpage() {
        return "detail-profile";
    }

    @GetMapping("/home/myprofile") // 마이페이지
    public String myprofilePage() {
        return "myprofile";
    }
}
