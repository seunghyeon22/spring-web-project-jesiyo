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
@Table(name = "cancel_tb")
@Getter
@Entity
public class Cancel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Cancel(Integer id, User seller, User buyer, Transaction transaction, String content, Timestamp createdAt) {
        this.id = id;
        this.seller = seller;
        this.buyer = buyer;
        this.transaction = transaction;
        this.content = content;
        this.createdAt = createdAt;
    }
}
