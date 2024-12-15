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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @Column(nullable = false)
    private Integer tryPrice;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Bid(Integer id, User buyer, Goods goods, Integer tryPrice, Timestamp createdAt) {
        this.id = id;
        this.buyer = buyer;
        this.goods = goods;
        this.tryPrice = tryPrice;
        this.createdAt = createdAt;
    }

    public void updatePrice(Integer price){
        this.tryPrice = price;
    }
}