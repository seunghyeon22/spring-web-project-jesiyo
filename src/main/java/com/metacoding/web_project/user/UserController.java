package com.metacoding.web_project.user;

import com.metacoding.web_project._core.CommonResp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm(Model model) {

        return "join";
    }
    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }

    @PostMapping("/check-id")
    public @ResponseBody int checkId(@RequestBody UserRequest.CheckIdDTO username){
        int result = userService.아이디중복확인(username);
        return result;
    }

    @GetMapping("/login-form")
    public String loginForm(String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }


    // /s/붙이기  신용점수 보이는 개인정보 페이지
    @GetMapping("/s/user-info/")
    public String userInfo(@AuthenticationPrincipal User user ,Model model) {
        int id = user.getId();
        UserResponse.CreditDTO credits = userService.내정보보기(id);
        model.addAttribute("model", credits);
        return "user-info";
    }

    // /s/붙이기 개인정보 수정페이지
    @GetMapping("/s/user-info/change-form")
    public String userInfoChangeForm(@AuthenticationPrincipal User user, Model model) {
        UserResponse.InfoDTO infoDTO = userService.유저정보보기(user.getId());
        model.addAttribute("info", infoDTO);
        return "user-info-change";
    }

    // 개인정보 수정 + 계좌등록하기
    @PostMapping("/s/user-info/change")
    public String userInfoChange(@AuthenticationPrincipal User user,UserRequest.UpdateDTO updateDTO,UserRequest.UpdateUserAccountDTO updateUserAccountDTO) {
        int id = user.getId();
        userService.유저정보수정하기(id,updateDTO,updateUserAccountDTO);
        return "redirect:/s/user-info/change-form";
    }


    @PostMapping("/s/user-info/pw-change")
    public String pwChange(@AuthenticationPrincipal User user, UserRequest.ChangePwDTO changePwDTO) {
        userService.비밀번호변경(user.getId(),changePwDTO);
        return "redirect:/s/user-info/change-form";
    }

    @GetMapping("/user-find-form")
    public String findUser() {
        return "user-find";
    }

    @PostMapping("/user-find-id")
    @ResponseBody
    public ResponseEntity<?> findId(@RequestBody UserRequest.FindUserDTO findUserDTO) {
        String result = String.valueOf(userService.아이디찾기(findUserDTO));
        return ResponseEntity.ok(Map.of("result", result));
    }

    // 인증
    @ResponseBody
    @GetMapping("/api/v1/authentication")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        CommonResp<UserDetails> resp = CommonResp.success(userDetails);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/user-find-pw")
    public  @ResponseBody Integer findPw(@RequestBody UserRequest.FindPwDTO findPwDTO) {
        int result = userService.비번찾기(findPwDTO);
        if (result > 0) { // 비밀번호 찾기 성공
            session.setAttribute("id", result);
        }
        return result; // 0 실패, 1 이상은 성공
    }

    @GetMapping("/change-pw-form")
    public String changepwForm(Model model) {
        return "change-pw";
    }

    @PostMapping("/change-pw")
    public String changepw(UserRequest.ChPwDTO pwDTO) {
        int id = (int) session.getAttribute("id");
        userService.비번변경(id,pwDTO);
        return "redirect:/";
    }

    @PostMapping("/s/user-info/withdrawal")
    public String withdraw(@AuthenticationPrincipal User user, UserRequest.WithdrawDTO withdrawDTO){
        userService.출금하기(user.getId(),withdrawDTO);
        System.out.println("송금해야할 계좌 : "+withdrawDTO.getOutAccount());
        return "redirect:/s/user-info/";
    }

    @PostMapping("/s/user-info/charging")
    public String charge(@AuthenticationPrincipal User user,UserRequest.ChargeDTO chargeDTO){
        userService.충전하기(user.getId(),chargeDTO);
        return "redirect:/s/user-info/";
    }

}