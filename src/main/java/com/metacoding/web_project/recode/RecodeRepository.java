package com.metacoding.web_project.recode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RecodeRepository {
    private final EntityManager em;

    // bid 데이터 -> recode로 옮겨담기
    public void save(Recode recode) {
        em.persist(recode);
    }

    public void save(List<Recode> recodes) {
        int batchSize = recodes.size()-1;

        em.persist(recodes);
    }

    // 조건에 맞는 행만 찾아서 유저의 기록을 반환하는 메서드
    public List<Recode> findFailRecodeByUserId(String divide, String search, Integer userId, Integer offset, Integer limit) {
        // 기본 SQL 쿼리 부분
        String baseSql = """
        SELECT MAX(r.id) AS id, r.buyer_id, r.goods_id, MAX(r.try_price) AS try_price, r.success_status, MAX(r.created_at) AS created_at 
        FROM recode_tb r 
        JOIN goods_tb g ON r.goods_id = g.id
        """;

        // 동적으로 조건 추가
        if (divide.equals("seller")) {
            baseSql += "JOIN user_tb u ON g.seller_id = u.id WHERE r.buyer_id = ? AND r.success_status = 0 AND u.name LIKE ? ";
        } else {
            baseSql += "WHERE r.buyer_id = ? AND r.success_status = 0 AND g.title LIKE ? ";
        }

        // 최종 SQL 쿼리 구성
        String finalSql = baseSql + """
        GROUP BY r.buyer_id, r.goods_id, r.success_status 
        ORDER BY MAX(r.id) 
        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
        """;

        // Query 객체 생성 및 매개변수 설정
        Query q = em.createNativeQuery(finalSql, Recode.class);
        q.setParameter(1, userId);
        q.setParameter(2, "%" + search + "%");
        q.setParameter(3, offset);
        q.setParameter(4, limit);

        // 쿼리 실행 및 결과 반환
        return q.getResultList();
    }

    // list의 아이디 값을 기준으로 성공한 행들의 List를 반환하는 메서드
    public List<Recode> findSuccessPrice(List<Integer> goodsIdList) {
        String query = "SELECT r FROM Recode r WHERE r.successStatus = 1 and r.goods.id IN :goodsIdList";
        Query q = em.createQuery(query);
        q.setParameter("goodsIdList", goodsIdList);
        return q.getResultList();
    }

    // 유저 아이디를 기준으로 입찰 실패한 모든 행의 개수를 반환하는 메서드
    public Integer countFailedRecodeByUserId(Integer userId, String divide, String search) {
        // 기본 SQL 쿼리 부분
        String baseSql = """
        SELECT COUNT(*) AS failed_count
        FROM (
            SELECT MAX(r.id) AS id, r.buyer_id, r.goods_id, MAX(r.try_price) AS try_price, r.success_status, MAX(r.created_at) AS created_at 
            FROM recode_tb r 
            JOIN goods_tb g ON r.goods_id = g.id
            """;

        // 동적으로 조건 추가
        if (divide.equals("seller")) {
            baseSql += "JOIN user_tb u ON g.seller_id = u.id WHERE r.buyer_id = ? AND r.success_status = 0 AND u.name LIKE ? ";
        } else {
            baseSql += "WHERE r.buyer_id = ? AND r.success_status = 0 AND g.title LIKE ? ";
        }

        // 최종 SQL 쿼리 구성
        String finalSql = baseSql + """
            GROUP BY r.buyer_id, r.goods_id, r.success_status 
            ORDER BY MAX(r.id)
        ) AS subquery
        """;

        // Query 객체 생성 및 매개변수 설정
        Query q = em.createNativeQuery(finalSql);
        q.setParameter(1, userId);
        q.setParameter(2, "%" + search + "%");

        // 쿼리 실행 및 결과 반환
        return ((Number) q.getSingleResult()).intValue();
    }
}
