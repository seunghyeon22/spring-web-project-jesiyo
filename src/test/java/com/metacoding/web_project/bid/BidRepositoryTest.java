package com.metacoding.web_project.bid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BidRepository.class)
@DataJpaTest
public class BidRepositoryTest {

    @Autowired
    private BidRepository bidRepository;

    @Test
    public void findAllBidsAndUser_test() {
        List<Bid> bidList = bidRepository.findAllBidsJoinAnotherInfo("");

        bidList.get(0).getBuyer().getName();
        bidList.get(0).getGoods().getSeller().getName();

    }
    // 경매중인 물품 목록 테스트
    @Test
    public void findAll() {
        // when (테스트할 메서드 실행)
        List<Bid> bidList = bidRepository.findAll();

        // then(eye) (결과 검증 / 출력으로 직접 확인은 eye)
        for (Bid bid : bidList) {
            System.out.println(bid.getId());
            System.out.println(bid.getGoods().getImgUrl());
            System.out.println(bid.getGoods().getCategory().getName());
            System.out.println(bid.getGoods().getEndAt());
            System.out.println(bid.getGoods().getStartingPrice());
            System.out.println(bid.getTryPrice());
        }
    }
}
