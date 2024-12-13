package com.metacoding.web_project.goods;

import com.metacoding.web_project._core.error.ex.Exception400;
import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "goods_tb")
@Getter
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

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
    // 3 : 경매 취소
    @Column(nullable = false)
    private Integer status;

    // status 기본값 0으로 설정
    @PrePersist
    public void prePersist(){
        if (this.status == null) {
            this.status = 0;
        }
    }

    @Builder
    public Goods(Integer id, String title, Category category, User seller, String content, String imgUrl, Integer startingPrice, Timestamp createdAt, Timestamp endAt, Integer status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.seller = seller;
        this.content = content;
        this.imgUrl = imgUrl;
        this.startingPrice = startingPrice;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.status = status;
    }

    // 상태 변경을 위한 메서드입니다.
    public void endAuction(){
        if (this.status == 1) {
            throw new Exception400("이미 경매가 종료되었습니다.");
        }
        this.status = 1; // 상태변경
        this.endAt = new Timestamp(System.currentTimeMillis());
    }
    // 경매 취소에 대한 상태 변경
    public void cancelAuction(){
//        if (this.status == 1) {
//            throw new Exception400("이미 경매가 종료되었습니다.");
//        }
        this.status = 3; // 상태변경
    }
}