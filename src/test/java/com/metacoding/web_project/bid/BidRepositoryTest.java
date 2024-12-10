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
}
