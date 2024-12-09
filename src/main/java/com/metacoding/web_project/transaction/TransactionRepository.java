package com.metacoding.web_project.transaction;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TransactionRepository {
    private final EntityManager em;
}
