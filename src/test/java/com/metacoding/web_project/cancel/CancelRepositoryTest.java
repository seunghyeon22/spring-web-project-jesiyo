package com.metacoding.web_project.cancel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(CancelRepository.class)
@DataJpaTest
public class CancelRepositoryTest {

    @Autowired
    private CancelRepository cancelRepository;

}
