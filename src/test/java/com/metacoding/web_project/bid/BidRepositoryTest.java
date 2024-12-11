package com.metacoding.web_project.bid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

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

    // 경매중인 물품(판매) 목록 테스트
    @Test
    public void findAll_test() {
        // when (테스트할 메서드 실행)
        List<Bid> bidList = bidRepository.findByBuyerIdForSell();

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

    public void findByGoodsDesc_Test(){
       Optional<Bid> bid =  bidRepository.findByGoodsDesc(1);
        System.out.println(bid.get().getTryPrice());
    }

    // 경매 참여중인 물품(구매) 목록 테스트
    @Test
    public void findByBuyerIdForBuy_test() {
        // given (테스트를 위한 입력값)
        Integer id = 1;

        // when (테스트할 메서드 실행)
        List<Bid> bidList = bidRepository.findByBuyerIdForBuy(id);

        // then(eye) (결과 검증 / 출력으로 직접 확인은 eye)
        for (Bid bid : bidList) {
            System.out.println(bid.getId());
            System.out.println(bid.getGoods().getTitle());
            System.out.println(bid.getBuyer().getId());
            System.out.println(bid.getGoods().getSeller().getName());
            System.out.println(bid.getGoods().getImgUrl());
            System.out.println(bid.getGoods().getCategory().getName());
            System.out.println(bid.getTryPrice());
            System.out.println("=======================");
        }
    }

    // 경매 참여중인 물품(구매)의 최고 입찰가 조회 테스트
    @Test
    public void findBestPrice_test() {
        // given (테스트를 위한 입력값)
        Integer id = 1;

        // when (테스트할 메서드 실행)
        Bid bid = bidRepository.findBestPrice(id);

        // then(eye) (결과 검증 / 출력으로 직접 확인은 eye)
        System.out.println(bid.getTryPrice());
    }
}
