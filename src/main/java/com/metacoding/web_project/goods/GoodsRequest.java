package com.metacoding.web_project.goods;

import lombok.Data;

import com.metacoding.web_project._core.util.FileUtil;
import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GoodsRequest {

    @Data
    public static class GoodsStatusDTO {
        private Integer id;

    }

    // 제품 등록 DTO
    @Data
    public static class GoodsSaveDTO {
        private String title;
        private Category category;
        private User seller;
        private String content;
        private MultipartFile imgUrl;
        private Integer startingPrice;
        private LocalDate endAt;

        public Goods toEntity() {

            return Goods.builder()
                    .title(title)
                    .category(category)
                    .seller(seller)
                    .content(content)
                    .imgUrl(FileUtil.fileSave(imgUrl))
                    .startingPrice(startingPrice)
                    .endAt(Timestamp.valueOf(endAt.atStartOfDay()))
                    .build();
        }
    }
}


