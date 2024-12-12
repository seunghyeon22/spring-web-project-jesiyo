package com.metacoding.web_project.bid;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BidRepository {
    private final EntityManager em;

    // 상세페이지 현재 최고 경매가 데이터 추출
    // limit 1 추가하여 getSingleResult 오류 발생 저지
    // 새로 생성된 물건 경매가가 없을 경우
    // Optional.empty 로 리포지토리에서 처리하고 GoodsService 클래스로 넘김
    public Optional<Bid> findByGoodsDesc(Integer id) {
        try{
            Query q = em.createNativeQuery("select * from bid_tb where goods_id = ? order by id desc limit 1", Bid.class);
            q.setParameter(1, id);
            return Optional.ofNullable((Bid) q.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // 사용자 입찰 시도 시 현재 최고금액 조회 (SELECT ~ FOR UPDATE)
    public Optional<Bid> findByGoodsIdDescWithLock(Integer id) {
        try{
            Query q = em.createNativeQuery("select * from bid_tb where goods_id = ? order by id desc limit 1 for update", Bid.class);
            q.setParameter(1, id);
            return Optional.ofNullable((Bid) q.getSingleResult());
        }catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void saveV1(Bid bid) {
        em.persist(bid);
    }
    
    // bid 테이블을 join하여 조회(goods,user)(관리자용)
    public List<Bid> findAllBidsJoinAnotherInfo(String query) {
        String sql = """
                select b from Bid b join fetch b.buyer join fetch b.goods g join fetch g.seller
                """;
        sql += query;
        Query q = em.createQuery(sql, Bid.class);
        return (List<Bid>) q.getResultList();
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

    // recode에 옮겨담을 경매가 끝난 물품의 기록들 꺼내는 메서드
    public List<Bid> findAllByGoodsId(Integer id) {

        String query = """
                select * from bid_tb where goods_id = ?
                """;
        Query q = em.createNativeQuery(query, Bid.class);
        q.setParameter(1, id);

        return q.getResultList();
    }

    public void deleteByGoodsId(int id) {
        Query q = em.createNativeQuery("delete from bid_tb where goods_id = ?");
        q.setParameter(1, id);
        q.executeUpdate();
    }


    //delete 메서드 작동확인용
    public Long countByGoodsId(int id) {
        Query q = em.createNativeQuery("select count(*) from bid_tb where goods_id = ?");
        q.setParameter(1, id);

        return (Long) q.getSingleResult();
    }

    // 조회해서 없으면 아무것도 없는 Optinal을 반환할 객체가 필요해서
    public Optional<Bid> findByGoodsDescIsNull(Integer id) {
        try {
            Query q = em.createNativeQuery("select * from bid_tb where goods_id = ? order by id desc limit 1", Bid.class);
            q.setParameter(1, id);
            return Optional.ofNullable((Bid) q.getSingleResult());
        } catch (
                NoResultException e) {
            return Optional.empty();
        }
    }

    // 경매중인 물품(판매) 목록 보기
    public List<Bid> findByBuyerIdForSell(Integer id) {

        String query = """
                select b.* from bid_tb b
                join goods_tb g on b.goods_id = g.id
                where g.status=0 and b.buyer_id = ?
                and b.try_price = (select max(b1.try_price) from bid_tb b1 where b1.goods_id = b.goods_id)
                """;

        Query q = em.createNativeQuery(query, Bid.class);
        q.setParameter(1, id);

        return q.getResultList(); // List 반환
    }

    // 경매 참여중인 물품(구매) 목록 보기
    public List<Bid> findByBuyerIdForBuy(Integer id) {

        String query = """ 
                select * from bid_tb 
                 where try_price in (select max(try_price) from bid_tb where buyer_id = ? group by goods_id)
                """;

        Query q = em.createNativeQuery(query, Bid.class);
        q.setParameter(1, id);
        
        return q.getResultList(); // List 반환

    }

    // 경매 참여중인 물품(구매)의 최고 입찰가 조회
    public Bid findMaxPrice(Integer goodsId) {

        String query = """
                select b.* from bid_tb b where b.goods_id = ? order by b.try_price desc limit 1
                """;
        Query q = em.createNativeQuery(query, Bid.class);
        q.setParameter(1, goodsId);

        return (Bid) q.getSingleResult();
    }
}
