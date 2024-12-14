package com.metacoding.web_project.category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryRepository {
    private final EntityManager em;

    public List<Category> findAllCategory() {
        Query q = em.createQuery("SELECT c FROM Category c", Category.class);
        return q.getResultList();
    }

    public void insertCategory(Category entity) {
        em.persist(entity);
    }

    // id를 통해 카테고리 1개를 가져오는 메서드
    public Category findCategoryById(Integer id) {
        return em.find(Category.class, id);
    }
}
