package com.metacoding.web_project.goods;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.metacoding.web_project._core.util.FormatDate.formatRemainingTime;

public class GoodsResponse {

    @Data
    public static class GoodsDetailDTO {
        // id값 받는 이유 -> 경매에 입찰 시도할때 goodsID필요
        // view 페이지에 goodsID 달아놓을려고
        Integer id;
        String title;
        String category;
        String seller;
        String content;
        String imgUrl;
        Integer startingPrice;

        Integer tryPrice;

        String endAtFront;

        Timestamp endAtBack;

        public GoodsDetailDTO(Goods goods, Integer bidTryPrice, String formattedEndAt) {
            this.id = goods.getId();
            this.title = goods.getTitle();
            this.category = goods.getCategory().getName();
            this.seller = goods.getSeller().getName();
            this.content = goods.getContent();
            this.imgUrl = goods.getImgUrl();
            this.startingPrice = goods.getStartingPrice();
            this.tryPrice = bidTryPrice;
            //종료기간
            this.endAtFront = formattedEndAt;
            this.endAtBack = goods.getEndAt();
        }
    }

    @Data
    public static class GoodsDTO {
      private Integer id;
      private String title;
      private String category;
      private String seller;
      private String imgUrl;
      private Integer startingPrice;
      private String tryPrice;
      private String endAt;

        @Builder
        public GoodsDTO(Goods goods, Integer bidTryPrice) {
            this.id = goods.getId();
            this.title = goods.getTitle();
            this.category = goods.getCategory().getName();
            this.seller = goods.getSeller().getName();
            this.imgUrl = goods.getImgUrl();
            this.startingPrice = goods.getStartingPrice();
            this.tryPrice = bidTryPrice==0? "입찰자가 없습니다.":"최고 입찰가: "+String.valueOf(bidTryPrice);
            this.endAt = formatRemainingTime(goods.getEndAt());
        }


    }
    @Data
    public static class UserGoodsDTO {
        private Integer id;
        private String title;
        private String category;
        private String imgUrl;
        private Integer startingPrice;
        private String tryPrice;
        private String endAt;

        @Builder
        public UserGoodsDTO(Goods goods, Integer bidTryPrice) {
            this.id = goods.getId();
            this.title = goods.getTitle();
            this.category = goods.getCategory().getName();
            this.imgUrl = goods.getImgUrl();
            this.startingPrice = goods.getStartingPrice();
            this.tryPrice = bidTryPrice==0? "입찰자가 없습니다.":"최고 입찰가: "+String.valueOf(bidTryPrice);
            this.endAt = formatRemainingTime(goods.getEndAt());
        }
    }

    @Data
    public static class KeyWordDTO {
        private String searchText;

        public KeyWordDTO(String searchText) {
            this.searchText = "'" + searchText + "'";
        }
    }
}
