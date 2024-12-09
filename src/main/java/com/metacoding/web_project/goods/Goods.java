package com.metacoding.web_project.goods;

import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "goods_tb")
@Getter
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sellerId;

    @Column(nullable = false)
    private String goodsContent;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private Integer startingPrice;

    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp endAt;

    @Column(nullable = false)
    private Integer status;

    public Goods(Integer goodsId, Category category, User sellerId, String goodsContent, String imgUrl, Integer startingPrice, Timestamp createdAt, Timestamp endAt, Integer status) {
        this.goodsId = goodsId;
        this.category = category;
        this.sellerId = sellerId;
        this.goodsContent = goodsContent;
        this.imgUrl = imgUrl;
        this.startingPrice = startingPrice;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.status = status;
    }
}