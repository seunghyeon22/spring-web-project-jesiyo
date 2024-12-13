package com.metacoding.web_project.recode;

import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@Import(RecodeRepository.class)
@DataJpaTest
public class RecodeRepositoryTest {

    @Autowired
    private RecodeRepository recodeRepository;

    @Test
    public void findRecodeByUserId_test() {
        List<Recode> recodeByUserId = recodeRepository.findRecodeByUserId(1, 0,  10);
        System.out.println("리스트 사이즈 : " + recodeByUserId.size());
        for (Recode recode : recodeByUserId) {
            System.out.println(recode.getGoods().getId());
            System.out.println(recode.getTryPrice());
        }

    }
}
