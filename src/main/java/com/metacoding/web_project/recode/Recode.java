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
public class Recode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goods goods;

    @Column(nullable = false)
    private Integer tryPrice;

    @Column(nullable = false)
    private Integer successStatus;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Recode(Integer id, User seller, Goods goods, Integer tryPrice, Integer successStatus, Timestamp createdAt) {
        this.id = id;
        this.seller = seller;
        this.goods = goods;
        this.tryPrice = tryPrice;
        this.successStatus = successStatus;
        this.createdAt = createdAt;
    }
}