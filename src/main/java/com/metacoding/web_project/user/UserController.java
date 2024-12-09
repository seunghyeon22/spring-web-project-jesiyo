package com.metacoding.web_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    @GetMapping("/login-form")
    public String loginForm() {
        return "main";
    }
}