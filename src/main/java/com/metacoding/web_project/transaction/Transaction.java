package com.metacoding.web_project.transaction;


import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Table(name = "transaction_tb")
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    // status :
    // 0 : 거래 확정 버튼 누르기 전
    // 1 : 거래 확정 버튼 누른 후
    @Column(nullable = false)
    private Integer buyer_status;

    @Column(nullable = false)
    private Integer seller_status;



    @Column(nullable = false)
    private Integer success_price;

    @Column(nullable = true)
    private Integer deliveryNum;

    @CreationTimestamp
    private Timestamp updatedAt;

    @Builder
    public Transaction(Integer id, Goods goods, User buyer, User seller, Integer buyer_status, Integer seller_status, Integer success_price, Integer deliveryNum) {
        this.id = id;
        this.goods = goods;
        this.buyer = buyer;
        this.seller = seller;
        this.buyer_status = buyer_status;
        this.seller_status = seller_status;
        this.success_price = success_price;
        this.deliveryNum = deliveryNum;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void updateStatus(Integer buyer_status, Integer seller_status, Integer deliveryNum) {
        if (buyer_status != null) {
            this.buyer_status = buyer_status;
        }
        if (seller_status != null) {
            this.seller_status = seller_status;
        }
        if (deliveryNum != null) {
            this.deliveryNum = deliveryNum;
        }
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}