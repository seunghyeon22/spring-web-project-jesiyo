package com.metacoding.web_project.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class FindIdDTO{
        private String username;
    }

    public static class InfoDTO{
        private String id;
        private String username;
        private String name;
        private String tel;
        private String postNum;
        private String addr;
        private String addrDetail;
    //    private String account;


        public InfoDTO(User user) {
            this.id = String.valueOf(user.getId());
            this.username = user.getUsername();
            this.name = user.getName();
            this.tel = user.getTel();
            this.postNum = user.getPostNum();
            this.addr = user.getAddr();
            this.addrDetail = user.getAddrDetail();
        }
    }

}
