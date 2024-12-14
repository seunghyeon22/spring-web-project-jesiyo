package com.metacoding.web_project.category;

import lombok.Data;

public class CategoryResponse {

    @Data
    public static class CategoryDTO {
        private Integer id;
        private String name;
        private String imgUrl;

        private boolean active = false;
        public CategoryDTO(Category category, boolean active) {
            this.id = category.getId();
            this.name = category.getName();
            this.imgUrl = category.getImgUrl();
            
            if (active) {
                this.active = true;
            }
        }
    }
}
