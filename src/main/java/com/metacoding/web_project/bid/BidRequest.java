package com.metacoding.web_project.bid;

import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import lombok.Data;

public class BidRequest {

    // 경매시도 데이터 받아내는 DTO
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


    @Data
    public static class CancelBidDTO {
        private Integer userId;
        private Integer goodsId;
        private Integer tryPrice;

    }

    @Data
    public static class ReBidDTO {
        private Integer userId;
        private Integer bidId;
        private Integer tryPrice;

    }

    @Data
    public static class ReBidRequestDTO {
        private Integer bidId;
        private Integer goodsId;
        private Integer tryPrice;
        private Integer reTryPrice;

    }

}
