package com.metacoding.web_project.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    // TODO : passwordEncoder를 이용해 변환한 비밀번호를 DB에 저장하여야 합니다.
    private final PasswordEncoder passwordEncoder;


    // POST 요청
    // /login 일때 호출 됨
    // key 값 -> username, password
    // Content-Type -> x-www-form-urlencoded
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return user;
    }
}