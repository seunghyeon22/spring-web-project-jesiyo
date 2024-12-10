package com.metacoding.web_project.recode;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecodeRepository {
    private final EntityManager em;

    // bid 데이터 -> recode로 옮겨담기
    public void save(Recode recode) {
        em.persist(recode);
    }
}
