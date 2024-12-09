package com.metacoding.web_project.goods;

import lombok.Data;

import static com.metacoding.web_project._core.util.FormatDate.formatRemainingTime;

public class GoodsResponse {

    @Data
    public static class GoodsDetailDTO {
        String title;
        String category;
        String seller;
        String content;
        String imgUrl;
        Integer startingPrice;
        Integer tryPrice;

        String endAt;

        public GoodsDetailDTO(Goods goods, Integer bidTryPrice) {
            this.title = goods.getTitle();
            this.category = goods.getCategory().getName();
            this.seller = goods.getSeller().getName();
            this.content = goods.getContent();
            this.imgUrl = goods.getImgUrl();
            this.startingPrice = goods.getStartingPrice();
            this.tryPrice = bidTryPrice;
            //종료기간
            this.endAt = formatRemainingTime(goods.getEndAt());
        }



    }
}
