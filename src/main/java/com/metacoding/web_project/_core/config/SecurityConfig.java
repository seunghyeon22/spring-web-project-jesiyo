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
                    r.requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/s/**").hasAnyRole("USER", "ADMIN")
                            .anyRequest().permitAll();
                }).formLogin(f -> f.loginPage("/login-form")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            User user = (User) authentication.getPrincipal();
                            HttpSession session = request.getSession();
                            session.setAttribute("sessionUser", user);
                            response.sendRedirect("/");
                        })
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/login-form?error=true");
                        }))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
