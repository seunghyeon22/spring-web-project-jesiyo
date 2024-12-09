package com.metacoding.web_project.bid;

import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "bid_tb")
@Getter
@Entity
public class Bid{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goodsId;

    @Column(nullable = false)
    private Integer tryPrice;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Bid(Integer bidId, User buyerId, Goods goodsId, Integer tryPrice, Timestamp createdAt) {
        this.bidId = bidId;
        this.buyerId = buyerId;
        this.goodsId = goodsId;
        this.tryPrice = tryPrice;
        this.createdAt = createdAt;
    }
}