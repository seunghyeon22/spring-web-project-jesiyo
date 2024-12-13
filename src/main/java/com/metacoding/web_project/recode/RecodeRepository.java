package com.metacoding.web_project.recode;

import jakarta.persistence.EntityManager;
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

}
