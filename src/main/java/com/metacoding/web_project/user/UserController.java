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

    @PostMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

    @PostMapping("/user-find-form")
    public String findUserForm() {
        return "user-find";
    }

    @PostMapping("/user-find")
    public String findUser(UserResponse.FindIdDTO findIdDTO,UserResponse.FindPasswordDTO findPasswordDTOwDTO) {
        userService.아이디찾기(findIdDTO);
        userService.비밀번호찾기(findPasswordDTOwDTO);
        return "redirect:/login";
    }
}