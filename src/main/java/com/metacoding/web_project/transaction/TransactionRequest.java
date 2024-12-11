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
        private Integer seller;
        private Integer successPrice;

        private Integer buyer;


        private Integer sellerStatus;
        private Integer buyerStatus;

        public Transaction toEntity(Integer buyerId) {

            return Transaction.builder()
                    .goods(Goods.builder().id(goodsId).build())
                    .seller(User.builder().id(seller).build())
                    .successPrice(successPrice)
                    .buyer(User.builder().id(buyerId).build())
                    .sellerStatus(1)
                    .buyerStatus(1)
                    .build();

        }

    }
}
