package com.metacoding.web_project.useraccount;
import com.metacoding.web_project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Table(name = "useraccount_tb")
@Getter
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer hasPrice;

    @Column(nullable = true)
    private String account;

    @Builder
    public UserAccount(Integer id, User user, Integer score, Integer hasPrice, String account) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.hasPrice = hasPrice;
        this.account = account;
    }

    public void updateUserInfo(Integer price) {
        this.hasPrice = hasPrice + price;
    }
    public void minusPrice(Integer price) {
        this.hasPrice = hasPrice - price;
    }
}