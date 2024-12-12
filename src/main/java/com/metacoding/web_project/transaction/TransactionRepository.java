package com.metacoding.web_project.transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionRepository {
    private final EntityManager em;

    public void save(Transaction transaction) {
        em.persist(transaction);
    }

    // 경매 성공한 구매자 ID 추출
    public Optional<Transaction> findSuccessBuyerByGoodsId(Integer id) {
        Query q = em.createNativeQuery("select * from transaction_tb where goods_id = ?", Transaction.class);

        q.setParameter(1, id);

        return Optional.ofNullable((Transaction) q.getSingleResult());
    }

    // Transaction 테이블을 join하여 조회(goods,user)(관리자용)
    public List<Transaction> findTransactionJoinAnotherInfo(String query, Integer offset, int limit) {
        String sql = """
                select t from Transaction t join fetch t.goods join fetch t.buyer join fetch t.seller
            """;
        sql += query;
        Query q = em.createQuery(sql, Transaction.class);
        q.setFirstResult(offset); // offset
        q.setMaxResults(limit); // limit
        return (List<Transaction>) q.getResultList();
    }

    // transaction 테이블에서 조건에 맞는 행의 개수를 반환하는 메서드
    public Integer findTransactionsCount(String queryStr) {
        String query = "SELECT COUNT(t) FROM Transaction t JOIN t.goods JOIN t.buyer JOIN t.seller";
        query += queryStr;
        Query q = em.createQuery(query);
        Long count = (Long) q.getSingleResult();
        return count.intValue();
    }


    // 낙찰 완료된 물품 목록 DTO(판매자 입장 / 판매 확정 안 누른 상태)
    public List<Transaction> findBySellerIdNotConfirmOfSell(Integer id) {
        String sql = """
                select * from transaction_tb where seller_id = ? and seller_status = 0
                """;

        Query q = em.createNativeQuery(sql, Transaction.class);
        q.setParameter(1, id);
        
        return q.getResultList();
    }

    // 낙찰된 물품(구매) 목록 DTO(구매 확정 누름, 안누름 전부 포함)
    public List<Transaction> findByBuyerIdForAllBuy(Integer id) {
        String sql = """
                select * from transaction_tb where buyer_id = ?
                """;

        Query q = em.createNativeQuery(sql, Transaction.class);
        q.setParameter(1, id);

        return q.getResultList();
    }
}
