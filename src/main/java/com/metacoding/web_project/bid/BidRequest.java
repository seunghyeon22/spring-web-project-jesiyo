package com.metacoding.web_project.bid;

import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import lombok.Data;

public class BidRequest {

    @Data
    public static class TryBidDTO {
        Integer tryPrice;
        Integer userId;
        Integer goodsId;

        public Bid toEntity(Integer userId) {
            return Bid.builder()
                    .buyer(User.builder().id(userId).build())
                    .goods(Goods.builder().id(goodsId).build())
                    .tryPrice(tryPrice)
                    .build();

        }
    }
}
