package com.metacoding.web_project.recode;

import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class RecodeRequest {

    @Data
    public static class saveDTO {
        private Integer id;
        private Integer buyerId;
        private Integer goodsId;
        private Integer tryPrice;
        private Integer successStatus;
        private Timestamp createdAt;

        public Recode toEntity(Bid bid) {
            return Recode.builder()
                    .buyer(User.builder().id(bid.getBuyer().getId()).build())
                    .goods(Goods.builder().id(bid.getGoods().getId()).build())
                    .tryPrice(bid.getTryPrice())
                    .successStatus(successStatus)
                    .createdAt(bid.getCreatedAt())
                    .build();
        }

    }
}
