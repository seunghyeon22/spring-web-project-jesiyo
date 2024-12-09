package com.metacoding.web_project.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class FindIdDTO{
        private String name;
        private String tel;

        public FindIdDTO(User user) {
            this.name = user.getName();
            this.tel = user.getTel();
        }
    }

    @Data
    public static class FindPasswordDTO{
        private String username;
        private String name;
        private String tel;

        public FindPasswordDTO(User user) {
            this.username = user.getName();
            this.name = user.getName();
            this.tel = user.getTel();
        }
    }
}
