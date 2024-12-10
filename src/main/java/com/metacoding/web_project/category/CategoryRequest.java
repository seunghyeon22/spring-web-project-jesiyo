package com.metacoding.web_project.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class CategoryRequest {

    @Data
    public static class CategoryDTO {
        private String name;
        private MultipartFile data;

        public Category toEntity(String imgUrl) {
            return Category.builder().name(name).imgUrl(imgUrl).build();
        }

    }
}
