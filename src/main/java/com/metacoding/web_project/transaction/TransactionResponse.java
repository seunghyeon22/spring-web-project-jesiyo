package com.metacoding.web_project.transaction;

import com.metacoding.web_project._core.util.FormatDate;
import lombok.Data;

public class TransactionResponse {

    

    @Data
    public static class TransactionDTO {
        private String title;
        private String sellerName;
        private String buyerName;
        private String success_price;
        private String update_at;
        private String sellerStatus = "대기중";
        private String buyerStatus = "대기중";


        public TransactionDTO(Transaction transaction) {


            this.title = transaction.getGoods().getTitle();
            this.sellerName = transaction.getSeller().getName();
            this.buyerName = transaction.getBuyer().getName();
            this.success_price = transaction.getSuccessPrice().toString();
            this.update_at = FormatDate.formatToyyyypMMpdd(transaction.getUpdatedAt());

            if (transaction.getSellerStatus() == 1) {
                this.sellerStatus = "판매확정";
            }
            if (transaction.getBuyerStatus() == 1) {
                this.buyerStatus = "구매확정";
            }
        }
    }

    // 낙찰된 물품(판매) 목록 DTO(판매 확정 안 누른 상태)
    @Data
    public static class CompleteAuctionDTO {
        private Integer id;
        private String title;
        private String categoryName;
        private String goodsImgUrl;
        private String updatedAt;
        private Integer successPrice;
        private Integer sellerStatus = 0; // 판매 확정 안 누른 상태
        private String buyerAddress;

        public CompleteAuctionDTO(Transaction transaction) {
            this.id = transaction.getId();
            this.title = transaction.getGoods().getTitle();
            this.categoryName = transaction.getGoods().getCategory().getName();
            this.goodsImgUrl = transaction.getGoods().getImgUrl();
            this.updatedAt = FormatDate.formatToyyyypMMpdd(transaction.getUpdatedAt());
            this.successPrice = transaction.getSuccessPrice();
            this.sellerStatus = transaction.getSellerStatus();
            this.buyerAddress = transaction.getBuyer().getAddr();
        }
    }

    // 낙찰된 물품(구매) 목록 DTO(구매 확정 누름, 안누름 전부 포함)
    @Data
    public static class ParticipatedAuctionDTO {
        private Integer id;
        private String title;
        private String sellerName;
        private String categoryName;
        private String goodsImgUrl;
        private Integer successPrice;
        private String deliveryNum;
        private Boolean buyerStatus = false;

        public ParticipatedAuctionDTO(Transaction transaction) {
            this.id = transaction.getId();
            this.title = transaction.getGoods().getTitle();
            this.sellerName = transaction.getGoods().getSeller().getName();
            this.categoryName = transaction.getGoods().getCategory().getName();
            this.goodsImgUrl = transaction.getGoods().getImgUrl();
            this.successPrice = transaction.getSuccessPrice();

            if (transaction.getDeliveryNum() != null) {
                this.deliveryNum = String.valueOf(transaction.getDeliveryNum());

            } else {
                this.deliveryNum = "";
            }

            if (transaction.getBuyerStatus() == 1) {
                this.buyerStatus = true;
            }
        }
    }
}