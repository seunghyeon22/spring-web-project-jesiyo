package com.metacoding.web_project.transaction;

import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import lombok.Data;

public class TransactionRequest {

    // 경매 종료 시 데이터 저장 요청으로 오는
    // 구매자 + 판매자 + 물품 id + 낙찰가 DTO
    // 주의점 구매자 ID는 service에서 낙찰가를 통해 bid_tb에서 조회해서 가져오게 된다.
    // 페이지에서 넘어올 때 구매자의 ID가 없기 때문에 따로 조회 필요
    @Data
    public static class SaveDTO {
        private Integer goodsId;
        private String seller;
        private Integer successPrice;

        private Integer buyer;


        private Integer sellerStatus;
        private Integer buyerStatus;
        private Integer transactionStatus;

        public Transaction toEntity(Goods goods, User buyer, User seller, Integer tryPrice) {

            return Transaction.builder()
                    .goods(goods)
                    .seller(seller)
                    .successPrice(tryPrice)
                    .buyer(buyer)
                    .sellerStatus(0)
                    .buyerStatus(0)
                    .transactionStatus(0)
                    .build();

        }
    }

    // 낙찰된 물품(판매) 화면 - 송장번호등록 DTO
    // transaction_tb 테이블의 delivery_num update
    @Data
    public static class UpdateDeliveryNumberDTO {
        private Integer transactionId;
        private String deliveryNumber;
    }

    // 낙찰된 물품(판매) 화면 - 판매 확정하기 DTO
    // transaction_tb 테이블의 seller_status = 1로 update
    @Data
    public static class UpdateSellerStatusDTO {
        private Integer transactionId;
        private Integer sellerStatus;
        private Integer buyerStatus;
    }

    // 낙찰된 물품(판매) 화면 - 판매 취소하기 DTO
    // transaction_tb 테이블의 transaction_status = 1로 update
    @Data
    public static class UpdateTransactionStatusForSellerDTO {
        private Integer transactionId;
        private Integer transactionStatus;
    }

    // 낙찰된 물품(구매) 화면 - 구매 확정하기 DTO
    // transaction_tb 테이블의 buyer_status = 1로 update
    @Data
    public static class UpdateBuyerStatusDTO {
        private Integer transactionId;
        private Integer buyerStatus;
        private Integer sellerStatus;
    }

    // 낙찰된 물품(구매) 화면 - 구매 취소하기 DTO
    // transaction_tb 테이블의 transaction_status = 2로 update
    @Data
    public static class UpdateTransactionStatusForBuyerDTO {
        private Integer transactionId;
        private Integer transactionStatus;
    }
}