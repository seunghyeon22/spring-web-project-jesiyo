package com.metacoding.web_project.report;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReportRepository {
    private final EntityManager em;


    //  신고 관련 테이블의 내용을 다른 테이블들과 join 하여 가져오는 메서드 (관리자)
    public List<Report> findReportJoinAnotherInfo(String query) {
        String sql = """
                select r from Report r join fetch r.transaction t join fetch t.seller join fetch t.buyer join fetch t.goods
            """;
        sql += query;
        Query q = em.createQuery(sql, Report.class);
        return (List<Report>) q.getResultList();
    }
}
