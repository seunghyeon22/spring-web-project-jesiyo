package com.metacoding.web_project.goods;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GoodsRepository {
    private final EntityManager em;

    public Optional<Goods> findById(Integer id) {
        return Optional.ofNullable(em.find(Goods.class, id));
    }
}
