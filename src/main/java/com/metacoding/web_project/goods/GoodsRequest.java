package com.metacoding.web_project.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.metacoding.web_project._core.util.FileUtil;
import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;

public class GoodsRequest {

    @Data
    public static class GoodsStatusDTO {
        private Integer goodsId;

    }

    // 제품 등록 DTO
    @Data
    public static class GoodsSaveDTO {
        private Integer sellerId;
        private String title;
        private Integer categoryId;
        private String content;
        private MultipartFile imgUrl;
        private Integer startingPrice;
        private LocalDate endAt;

        public Goods toEntity(Category categoryEntity, User sellerEntity) {
            return Goods.builder()
                    .title(title)
                    .category(categoryEntity)
                    .seller(sellerEntity)
                    .content(content)
                    .imgUrl(FileUtil.fileSave(imgUrl))
                    .startingPrice(startingPrice)
                    .endAt(Timestamp.valueOf(endAt.atStartOfDay()))
                    .build();
        }
    }


    @Data
    public static class SeacherGoodsDTO {
        private String select;
        private String keyword;
        private Integer page;
        private Integer line;


        @Builder
        public SeacherGoodsDTO(String select, String keyword, Integer page, Integer line) {
            this.select = select;
            this.keyword = keyword;
            this.page = page;
            this.line = line;
        }
    }

}