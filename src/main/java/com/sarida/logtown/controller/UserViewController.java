package com.sarida.logtown.controller;

import com.sarida.logtown.dto.UserResponseDto;
import com.sarida.logtown.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping("/users/{username}")
    public String viewUserPage(Model model, @PathVariable String username) {
        UserResponseDto userResponseDto = userService.getSelectUsername(username);
        model.addAttribute("userResponseDto", userResponseDto);

        return "user";
    }

//    @GetMapping("/myself")
//    public String viewUserPage(Model model) {
//        UserResponseDto userResponseDto = userService.getMyself();
//        model.addAttribute("userResponseDto", userResponseDto);
//
//        return "user";
//    }
}
