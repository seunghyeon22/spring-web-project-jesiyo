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
    // limit 1 추가하여 getSingleResult 오류 발생 저지
    public Optional<Bid> findByGoodsDesc(Integer id) {
        Query q = em.createNativeQuery("select * from bid_tb where goods_id = ? order by id desc limit 1", Bid.class);
        q.setParameter(1, id);
        return Optional.ofNullable((Bid) q.getSingleResult());
    }

    public void saveV1(Bid bid) {
        em.persist(bid);
    }

    // 최종 낙찰 금액을 조회해서 구매자 ID 찾아내는 메서드
    public Optional<Bid> findByTryPrice(Integer tryPrice) {

        String query = """
                select b.* from bid_tb b
                join `user_tb` u on b.buyer_id = u.id
                where b.try_price = ?
                """;

        Query q = em.createNativeQuery(query, Bid.class);
        q.setParameter(1, tryPrice);
        return Optional.ofNullable((Bid) q.getSingleResult());
    }
}
