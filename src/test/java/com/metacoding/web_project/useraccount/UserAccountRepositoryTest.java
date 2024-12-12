package com.metacoding.web_project.useraccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(UserAccountRepository.class)
@DataJpaTest
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    public void findByUsername_test() {
        UserAccount cos = userAccountRepository.findByUsername("cos");
        if (cos != null) {
            System.out.println("널이 아니에용");
        }
    }

    @Test
    public void findById_test() {
        Integer id = 1;
        UserAccount ua = userAccountRepository.findById(id);
        System.out.println(ua.getUser().getName());

    }
}
