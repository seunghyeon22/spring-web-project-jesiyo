package com.metacoding.web_project.bid;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BidRepository {
    private final EntityManager em;
}
