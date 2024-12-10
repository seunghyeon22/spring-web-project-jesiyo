package com.metacoding.web_project.recode;

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

        public Recode toEntity() {
            return Recode.builder()
                    .id(id)
                    .buyer(User.builder().id(buyerId).build())
                    .goods(Goods.builder().id(goodsId).build())
                    .tryPrice(tryPrice)
                    .successStatus(successStatus)
                    .createdAt(createdAt)
                    .build();
        }

    }
}
