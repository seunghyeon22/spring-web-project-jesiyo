package com.metacoding.web_project.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "category_tb")
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(unique = true, nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String categoryImg;

    @Builder
    public Category(Integer categoryId, String categoryName, String categoryImg) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
    }
}
