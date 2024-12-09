package com.metacoding.web_project.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;



@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 5)
    private String postNum;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false)
    private String addrDetail;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String role;

    @Builder
    public User(Integer userId, String username, String password, String name, String postNum, String addr, String addrDetail, String birth, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.postNum = postNum;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.birth = birth;
        this.role = role;
    }

    // 사용자의 권한을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 계정이 만료되지 않았는지 확인
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨 있지 않은지 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 상태인지 확인
    @Override
    public boolean isEnabled() {
        return true;
    }
}