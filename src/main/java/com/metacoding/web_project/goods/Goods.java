package com.metacoding.web_project.goods;

import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private Integer startingPrice;

    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp endAt;

    // 0 : 입찰 중 (입찰 가능 상태)
    // 1 : 낙찰 완료 (입찰 불가능 상태)
    // 2 : 최종 거래 완료 (입찰 불가능 상태 + 판매자, 구매자가 모두 거래 확정 버튼을 눌렀을 때)
    @Column(nullable = false)
    private Integer status;

    @Builder
    public Goods(Integer id, Category category, User seller, String content, String imgUrl, Integer startingPrice, Timestamp createdAt, Timestamp endAt, Integer status) {
        this.id = id;
        this.category = category;
        this.seller = seller;
        this.content = content;
        this.imgUrl = imgUrl;
        this.startingPrice = startingPrice;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.status = status;
    }
}