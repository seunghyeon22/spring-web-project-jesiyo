package com.metacoding.web_project.goods;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GoodsRepository {
    private final EntityManager em;
}
