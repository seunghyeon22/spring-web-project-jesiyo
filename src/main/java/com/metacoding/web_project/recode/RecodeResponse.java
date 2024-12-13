package com.metacoding.web_project.recode;

import com.metacoding.web_project._core.util.FormatDate;
import lombok.Data;

public class RecodeResponse {

    @Data
    public static class ParticipateFailDTO {
        private String title;
        private String sellerName;
        private Integer myPrice;
        private Integer finalPrice;
        private String endAt;

        public ParticipateFailDTO(Recode failRecode, int finalPrice) {

            this.title = failRecode.getGoods().getTitle();
            this.sellerName = failRecode.getGoods().getSeller().getName();
            this.myPrice = failRecode.getTryPrice();
            this.finalPrice = finalPrice;
            this.endAt = FormatDate.formatToyyyypMMpdd(failRecode.getCreatedAt());;
        }
    }
}
