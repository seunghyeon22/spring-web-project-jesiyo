package com.metacoding.web_project.goods;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import com.metacoding.web_project._core.util.FileUtil;
import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
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
        @NotNull(message = "판매자 id를 확인할 수 없습니다.")
        private Integer sellerId;

        @NotNull(message = "이미지를 업로드 하세요")
        private MultipartFile imgUrl;

        @NotBlank(message = "물품 이름 포함하여 제목을 입력해주세요")
        private String title;

        @NotNull(message = "카테고리를 선택해주세요")
        private Integer categoryId;

        @NotBlank(message = "내용을 입력해주세요")
        private String content;

        @NotNull(message = "최소 입찰가를 입력해주세요")
        @Min(value = 1, message = "최소 입찰가는 1원 보다 높아야 합니다.")
        private Integer startingPrice;

        @NotNull(message = "경매 종료일을 선택하세요")
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