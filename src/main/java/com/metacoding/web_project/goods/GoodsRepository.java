package com.metacoding.web_project.goods;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GoodsRepository {
    private final EntityManager em;

    public Optional<Goods> findById(Integer id) {
        return Optional.ofNullable(em.find(Goods.class, id));
    }

    // 제품 등록
    public void save(Goods goods) {
        em.persist(goods);
    }

    public Optional<List<Goods>> findByCategoryId(Integer categoryId) {
        String sql = """
                select g from Goods g left join fetch g.seller left join fetch g.category where g.category.id=:categoryId and g.status=:status
                """;
        Query query = em.createQuery(sql);
        query.setParameter("categoryId", categoryId);
        query.setParameter("status", 0);
        return Optional.ofNullable(query.getResultList());
    }

}
