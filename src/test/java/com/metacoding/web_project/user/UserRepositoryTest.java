package com.metacoding.web_project.user;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    @Test
    public void login_test(){
        String username = "ssar";
        String password = "$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m";

        //when
        userRepository.login(username, password);

    }
}
