package com.metacoding.web_project.recode;


import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "recode_tb")
@Getter
@Entity
public class Recode{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @Column(nullable = false)
    private Integer tryPrice;

    @Column(nullable = false)
    private Integer successStatus;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Recode(Integer recordId, User sellerId, Goods goods, Integer tryPrice, Integer successStatus, Timestamp createdAt) {
        this.recordId = recordId;
        this.sellerId = sellerId;
        this.goods = goods;
        this.tryPrice = tryPrice;
        this.successStatus = successStatus;
        this.createdAt = createdAt;
    }
}