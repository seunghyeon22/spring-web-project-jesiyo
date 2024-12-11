package com.metacoding.web_project._core.config;

import com.metacoding.web_project.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
        );

        http.authorizeHttpRequests(r -> {
            r.requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 권한 필요
                    .requestMatchers("/s/**").hasAnyRole("USER", "ADMIN") // /s/** 경로는 USER 또는 ADMIN 권한 필요
                    .anyRequest().permitAll();
        }).formLogin(f -> f.loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    User user = (User) authentication.getPrincipal();
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", user);
                    response.sendRedirect("/");
                }))
          .logout(logout -> logout
              .logoutUrl("/logout") // 로그아웃 URL
              .logoutSuccessUrl("/") // 로그아웃 후 리다이렉트할 URL
              .invalidateHttpSession(true) // 세션 무효화
              .deleteCookies("JSESSIONID") // 쿠키 삭제
          );

        return http.build();
    }
}
