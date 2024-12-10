package com.metacoding.web_project.bid;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BidRepository {
    private final EntityManager em;

    // 상세페이지 현재 최고 경매가 데이터 추출
    public Optional<Bid> findByGoodsDesc(Integer id) {
        Query q = em.createNativeQuery("select * from bid_tb where goods_id = ? order by id desc ", Bid.class);
        q.setParameter(1, id);
        return Optional.ofNullable((Bid) q.getSingleResult());
    }

    public void saveV1(Bid bid) {
        em.persist(bid);
    }
}
