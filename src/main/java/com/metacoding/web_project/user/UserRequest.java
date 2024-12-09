package com.metacoding.web_project.user;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    @Data
    public static class JoinDTO{
        private Integer id;
        private String username;
        private String password;
        private String name;
        private String postNum;
        private String addr;
        private String addrDetail;
        private String birth;
        private String role;

        public User toEntity(PasswordEncoder passwordEncoder){
            String encpw = passwordEncoder.encode(password);
        User user = new User(null, username, encpw, name, postNum, addr, addrDetail, birth, role);
        return user;

        }
    }

}