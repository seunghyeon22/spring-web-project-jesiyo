package com.metacoding.web_project.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(GoodsRepository.class)
@DataJpaTest
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

}
