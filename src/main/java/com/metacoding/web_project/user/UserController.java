package com.metacoding.web_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {

        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }
}