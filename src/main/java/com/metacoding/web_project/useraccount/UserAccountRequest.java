package com.metacoding.web_project.useraccount;

import lombok.Data;

public class UserAccountRequest {

    @Data
    public static class UpdateHasPriceDTO {
        private Integer id;
        private Integer price;
    }
}
