package com.metacoding.web_project.user;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    @Data
    public static class LoginDTO{
        private String username;
        private String password;

        public LoginDTO(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @Data
    public static class JoinDTO{
        private Integer id;
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String tel;
        @NotBlank
        private String name;
        @NotBlank
        private String postNum;
        @NotBlank
        private String addr;
        @NotBlank
        private String addrDetail;
        @NotBlank
        private String birth;
        private String role;

        public User toEntity(PasswordEncoder passwordEncoder){
            String encpw = passwordEncoder.encode(password);
            User user = new User(null, username, encpw, name, tel, postNum, addr, addrDetail, birth, role);
        return user;

        }
    }

    @Data
    public static class FindUserDTO {
        @NotBlank
        private String name;
        @NotBlank
        private String tel;
        private String username;
    }

    @Data
    public class UpdateDTO {
        private String tel;
        private String postNum;
        private String addr;
        private String addrDetail;
//        private String account;


    }
    @Data
    public class ChangePwDTO {
        private String password;
        private String newPassword;

    }
}