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
    private Integer buyerStatus;

    @Column(nullable = false)
    private Integer sellerStatus;

    // 0 : 판매/구매 취소 버튼 누르기 전
    // 1 : 판매 취소 버튼 누른 후
    // 2 : 구매 취소 버튼 누른 후
    @Column(nullable = false)
    private Integer transactionStatus;

    
    @Column(nullable = false)
    private Integer successPrice;

    @Column(nullable = true)
    private String deliveryNum; // 송장번호

    @CreationTimestamp
    private Timestamp updatedAt;

    @Builder
    public Transaction(Integer id, Goods goods, User buyer, User seller, Integer buyerStatus, Integer sellerStatus, Integer successPrice, String deliveryNum, Integer transactionStatus, Timestamp updatedAt) {
        this.id = id;
        this.goods = goods;
        this.buyer = buyer;
        this.seller = seller;
        this.buyerStatus = buyerStatus;
        this.sellerStatus = sellerStatus;
        this.transactionStatus = transactionStatus;
        this.successPrice = successPrice;
        this.deliveryNum = deliveryNum;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        this.transactionStatus = transactionStatus;
    }

    public void updateStatus(Integer buyerStatus, Integer sellerStatus, Integer transactionStatus, String deliveryNum) {
        if (buyerStatus != null) {
            this.buyerStatus = buyerStatus;
        }
        if (sellerStatus != null) {
            this.sellerStatus = sellerStatus;
        }
        if (transactionStatus != null) {
            this.transactionStatus = transactionStatus;
        }
        if (deliveryNum != null) {
            this.deliveryNum = deliveryNum;
        }
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}