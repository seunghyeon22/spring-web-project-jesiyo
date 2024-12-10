package com.metacoding.web_project.goods;

import com.metacoding.web_project.category.Category;
import com.metacoding.web_project.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.sql.Timestamp;
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

    // 제품 등록 테스트
    @Test
    public void save_test() {
        // given (테스트를 위한 입력값)
        Category category = Category.builder().id(2).build();
        User seller = User.builder().id(1).build();
        Goods goods = Goods.builder()
                .title("타이틀2")
                .category(category)
                .seller(seller)
                .content("내용2")
                .imgUrl("없음")
                .startingPrice(2000)
                .endAt(Timestamp.valueOf("2024-12-11 22:00:00"))
                .status(0)
                .build();

        // when (테스트할 메서드 실행)
        goodsRepository.save(goods);
        Integer savedId = goods.getId();

        // then(eye) (결과 검증 / 출력으로 직접 확인은 eye)
        Optional<Goods> goods1 = goodsRepository.findById(savedId);
        System.out.println(goods1.get().getTitle());
        System.out.println(goods1.get().getCategory().getId());
        System.out.println(goods1.get().getSeller().getId());
        System.out.println(goods1.get().getContent());
        System.out.println(goods1.get().getImgUrl());
        System.out.println(goods1.get().getStartingPrice());
        System.out.println(goods1.get().getEndAt());
        System.out.println(goods1.get().getStatus());
    }

}
