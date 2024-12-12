package com.metacoding.web_project.user;

import com.metacoding.web_project.useraccount.UserAccount;
import lombok.Data;

import java.util.List;

public class UserResponse {

    @Data
    public static class FindIdDTO{
        private String username;
    }

    @Data
    public static class InfoDTO{
        private String id;
        private String username;
        private String name;
        private String tel;
        private String postNum;
        private String addr;
        private String addrDetail;
        private String account;


        public InfoDTO(UserAccount user) {
            this.id = String.valueOf(user.getId());
            this.username = user.getUser().getUsername();
            this.name = user.getUser().getName();
            this.tel = user.getUser().getTel();
            this.postNum = user.getUser().getPostNum();
            this.addr = user.getUser().getAddr();
            this.addrDetail = user.getUser().getAddrDetail();
            this.account = user.getAccount();
        }
    }

    @Data
    public static class CreditDTO{
        private Integer id;
        private String username;
        private Integer score;
        private Integer hasPrice;

        public CreditDTO(UserAccount userAccount) {
            this.id = userAccount.getUser().getId();
            this.username = userAccount.getUser().getUsername();
            this.score = userAccount.getScore();
            this.hasPrice = userAccount.getHasPrice();
        }
    }

    @Data
    public static class FindUserDTO{
        private String username;
    }

}
