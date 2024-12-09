package com.metacoding.web_project.cancel;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CancelRepository {
    private final EntityManager em;
}
