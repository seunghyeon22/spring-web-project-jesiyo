package com.metacoding.web_project.report;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReportRepository {
    private final EntityManager em;
}
