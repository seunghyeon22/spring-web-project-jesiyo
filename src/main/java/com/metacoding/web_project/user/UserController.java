package com.metacoding.web_project.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
    // /s/붙이기  신용점수 보이는 개인정보 페이지
    @GetMapping("/user-info/{id}")
    public String userInfo(@PathVariable("id") int id ,Model model) {

        return "user-info";
    }

    // /s/붙이기 개인정보 수정페이지
    @GetMapping("/user-info/{id}/change-form")
    public String userInfoChangeForm(@PathVariable("id") int id, Model model) {
        UserResponse.InfoDTO infoDTO = userService.유저정보보기(id);
        model.addAttribute("info", infoDTO);
        return "user-info-change";
    }

    // 개인정보 수정
    @PostMapping("/user-info/{id}/change")
    public String userInfoChange(@PathVariable("id") int id,UserRequest.UpdateDTO updateDTO) {
        userService.유저정보수정하기(id,updateDTO);
        return "redirect:/user-info/{id}";
    }


    @PostMapping("/user-info/{id}/pw-change")
    public String pwChange(@PathVariable("id") int id, UserRequest.ChangePwDTO changePwDTO) {
        userService.비밀번호변경(id,changePwDTO);
        System.out.println(changePwDTO);
        return "redirect:/user-info/{id}";
    }

//    @PostMapping("/add-account")




}