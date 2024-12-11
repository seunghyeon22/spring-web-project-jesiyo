package com.metacoding.web_project.goods;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(GoodsService.class)
@SpringBootTest
public class GoodServiceTest {

    @Autowired
    public GoodsService goodsService;

    @Test
    public void getGoodsInfo(){
        GoodsResponse.GoodsDetailDTO d = goodsService.getGoodsInfo(2);
        System.out.println(d.getTitle());
        System.out.println(d.getTryPrice());
    }

    @Test
    public void getGoodsList_Test(){
        Integer categoryId = 1;
        List<GoodsResponse.GoodsDTO> s = goodsService.getGoodsList(categoryId);

        for(GoodsResponse.GoodsDTO g : s){
            System.out.println(g.getId());
            System.out.println(g.getSeller());
            System.out.println(g.getImgUrl());
            System.out.println(g.getTryPrice());
        }

    }

}
