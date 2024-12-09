package com.metacoding.web_project.goods;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;


@Import(GoodsRepository.class)
@DataJpaTest
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void findById_test(){
        Integer id = 1;

        Optional<Goods> result = goodsRepository.findById(id);
        Goods goods = result.get();

        System.out.println(goods.getTitle());
        System.out.println(goods.getSeller().getName());

    }

}
