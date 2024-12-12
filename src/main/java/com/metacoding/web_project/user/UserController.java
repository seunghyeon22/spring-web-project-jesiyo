package com.metacoding.web_project.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public String loginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }


    // /s/붙이기  신용점수 보이는 개인정보 페이지
    @GetMapping("/user-info/{id}")
    public String userInfo(@PathVariable("id") int id ,Model model) {
        UserResponse.CreditDTO credits = userService.내정보보기(id);
        model.addAttribute("model", credits);
        return "user-info";
    }

    // /s/붙이기 개인정보 수정페이지
    @GetMapping("/user-info/{id}/change-form")
    public String userInfoChangeForm(@PathVariable("id") int id, Model model) {
        UserResponse.InfoDTO infoDTO = userService.유저정보보기(id);
        model.addAttribute("info", infoDTO);
        return "user-info-change";
    }

    // 개인정보 수정 + 계좌등록하기
    @PostMapping("/user-info/{id}/change")
    public String userInfoChange(@PathVariable("id") int id,UserRequest.UpdateDTO updateDTO) {
        userService.유저정보수정하기(id,updateDTO);
        System.out.println(updateDTO);
        return "redirect:/user-info/{id}";
    }


    @PostMapping("/user-info/{id}/pw-change")
    public String pwChange(@PathVariable("id") int id, UserRequest.ChangePwDTO changePwDTO) {
        userService.비밀번호변경(id,changePwDTO);
        System.out.println(changePwDTO);
        return "redirect:/user-info/{id}/change-form";
    }

    // 아이디/비밀번호 찾기
    @GetMapping("/user-find-form")
    public String findUser(UserRequest.FindUserDTO findUserDTO) {
        return "user-find";
    }

/*    @PostMapping("/user-find")
    public ResponseEntity<?> find(@RequestBody UserRequest.FindUserDTO findUserDTO, Model model) {
        String result = String.valueOf(userService.유저찾기(findUserDTO));
        System.out.println(result);
        return result;
    }*/
@PostMapping("/user-find")
@ResponseBody
public ResponseEntity<?> find(@RequestBody UserRequest.FindUserDTO findUserDTO) {
    try {
        // userService를 통해 유저를 Optional로 반환
        String result = String.valueOf(userService.유저찾기(findUserDTO));
        // Optional 검사 후 유저 정보 반환
        if (result != null) {
            // Map으로 JSON 형태의 응답 생성
            return ResponseEntity.ok(Map.of("username", result));
        } else {
            // 유저가 없을 경우 에러 메시지와 함께 404 상태 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
    } catch (Exception e) {
        // 예외 발생 시 500 상태와 에러 메시지 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error", "message", e.getMessage()));
    }
}


/*    @PostMapping("/user-find")
    @ResponseBody
    public ResponseEntity<?> find(@RequestBody UserRequest.FindUserDTO findUserDTO) {
        // userService를 통해 유저를 Optional로 반환
        Optional<User> result = userService.유저찾기(findUserDTO.getTel(), findUserDTO.getName());

        // Optional 검사 후 유저 정보 반환
        if (result.isPresent()) {
            User user = result.get();
            // Map으로 JSON 형태의 응답 생성
            return ResponseEntity.ok(Map.of("username", user.getUsername()));
        } else {
            // 유저가 없을 경우 null 반환
            return ResponseEntity.ok(null);
        }
    }*/


}