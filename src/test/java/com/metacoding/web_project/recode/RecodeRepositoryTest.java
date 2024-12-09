package com.metacoding.web_project.recode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(RecodeRepository.class)
@DataJpaTest
public class RecodeRepositoryTest {

    @Autowired
    private RecodeRepository recodeRepository;

}
