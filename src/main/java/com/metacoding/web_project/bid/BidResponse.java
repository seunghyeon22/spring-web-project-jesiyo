package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.util.FormatDate;
import lombok.Data;

import java.sql.Timestamp;
import java.text.DateFormat;

public class BidResponse {
    @Data
    public static class BidDTO{

        private String goodsName;
        private String sellerName;
        private String buyerName;
        private Integer starting_price;
        private Integer tryPrice;
        private String goodsCreatedAt;
        private String bidCreatedAt;

        public BidDTO(Bid bid) {
            this.goodsName = bid.getGoods().getTitle();
            this.sellerName = bid.getGoods().getSeller().getName();
            this.buyerName = bid.getBuyer().getName();
            this.starting_price = bid.getGoods().getStartingPrice();
            this.tryPrice = bid.getTryPrice();
            this.goodsCreatedAt = FormatDate.formatToyyyypMMpdd(bid.getGoods().getCreatedAt());
            this.bidCreatedAt = FormatDate.formatToyyyypMMpdd(bid.getCreatedAt());

        }
    }
}
