package com.metacoding.web_project.category;

import lombok.Data;

public class CategoryResponse {

    @Data
    public static class CategoryDTO {
        private Integer id;
        private String name;
        private String imgUrl;

        public CategoryDTO(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.imgUrl = category.getImgUrl();
        }
    }
}
