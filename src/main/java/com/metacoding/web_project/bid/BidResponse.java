package com.metacoding.web_project.bid;

import lombok.Data;

import java.sql.Timestamp;

import com.metacoding.web_project._core.util.FormatDate;


public class BidResponse {

    // 경매 참여중인 물품(구매) 목록 DTO
    @Data
    public static class ParticipatingAuctionDTO {
        private Integer id;
        private String title;
        private Integer buyerId;
        private String sellerName;
        private String goodsImgUrl;
        private String categoryName;
        private Integer buyerTryPrice;
        private Integer maxPrice;

        // 생성자
        public ParticipatingAuctionDTO(Bid bid) {
            this.id = bid.getId();
            this.title = bid.getGoods().getTitle();
            this.buyerId = bid.getBuyer().getId();
            this.sellerName = bid.getGoods().getSeller().getName();
            this.goodsImgUrl = bid.getGoods().getImgUrl();
            this.categoryName = bid.getGoods().getCategory().getName();
            this.buyerTryPrice = bid.getTryPrice();
            this.maxPrice = maxPrice;
        }
    }

    // 경매중인 물품(판매) 목록 DTO
    @Data
    public static class BeingAuctionedDTO {
        private Integer id;
        private Integer goodsId;
        private String title;
        private String goodsImgUrl;
        private String categoryName;
        private String endAt;
        private Integer startingPrice;
        private Integer tryPrice;

        // 생성자
        public BeingAuctionedDTO(Bid bid) {
            this.id = bid.getId();
            this.goodsId = bid.getGoods().getId();
            this.title = bid.getGoods().getTitle();
            this.goodsImgUrl = bid.getGoods().getImgUrl();
            this.categoryName = bid.getGoods().getCategory().getName();
            this.endAt = FormatDate.formatToyyyypMMpdd(bid.getGoods().getEndAt());
            this.startingPrice = bid.getGoods().getStartingPrice();
            this.tryPrice = bid.getTryPrice();
        }
    }

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

    @Data
    public static class adminBidPageStatusDTO{

        private String divide;
        private String search;

        public adminBidPageStatusDTO(String divide, String search) {
            this.divide = divide;
            this.search = search;
        }
    }
}
