package com.metacoding.web_project.transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
