package com.metacoding.web_project.cancel;

import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "cancle_tb")
@Getter
@Entity
public class Cancel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cancleId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transactionId;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Cancel(Integer cancleId, User sellerId, User buyerId, Transaction transactionId, String content, Timestamp createdAt) {
        this.cancleId = cancleId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.transactionId = transactionId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
