package com.metacoding.web_project.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private Integer id;

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

    // role은 DB에 저장할 때 'ROLE_ADMIN' 과 같이 앞에 'ROLE_'을 붙여야 정상 작동합니다.
    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String tel;

    @Builder
    public User(Integer id, String username, String password, String name, String postNum, String addr, String addrDetail, String birth, String role, String tel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.postNum = postNum;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.birth = birth;
        this.role = role;
        this.tel = tel;
    }




    // 사용자의 권한을 반환
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        // role을 GrantedAuthority로 변환하여 반환
        return List.of(new SimpleGrantedAuthority(role));
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