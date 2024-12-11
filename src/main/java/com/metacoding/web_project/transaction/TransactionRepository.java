package com.metacoding.web_project.transaction;

import com.metacoding.web_project.bid.Bid;
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
    public List<Transaction> findAllTransactionJoinAnotherInfo(String query) {
        String sql = """
                select t from Transaction t join fetch t.goods join fetch t.buyer join fetch t.seller
            """;
        sql += query;
        Query q = em.createQuery(sql, Transaction.class);
        return (List<Transaction>) q.getResultList();
    }
}
