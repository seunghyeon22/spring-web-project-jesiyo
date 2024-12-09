package com.metacoding.web_project.category;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CategoryRepository {
    private final EntityManager em;
}
