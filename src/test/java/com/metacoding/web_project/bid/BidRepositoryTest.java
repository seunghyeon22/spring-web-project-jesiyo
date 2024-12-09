package com.metacoding.web_project.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BidRepository.class)
@DataJpaTest
public class BidRepositoryTest {

    @Autowired
    private BidRepository bidRepository;

}
