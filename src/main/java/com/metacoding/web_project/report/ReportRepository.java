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
    
    // 신고 하기(등록)
    public void save(Report report) {
        em.persist(report);
    }

    //  신고 관련 테이블의 내용을 다른 테이블들과 join 하여 가져오는 메서드 (관리자)
    public List<Report> findReportJoinAnotherInfo(String query, int offset, int limit) {
        String sql = """
                    select r from Report r join fetch r.transaction t join fetch t.seller join fetch t.buyer join fetch t.goods
                """;
        sql += query;
        Query q = em.createQuery(sql, Report.class);
        q.setFirstResult(offset); // offset
        q.setMaxResults(limit); // limit
        return (List<Report>) q.getResultList();
    }

    // report id 로 1건을 조회하는 메서드 (관리자용)
    // 추후 다른 여러값들을 get 하기 때문에 일부러 find 대신 join 문을 사용
    public Report findById(String reportId) {
        String sql = """
                    select r from Report r join fetch r.transaction t join fetch t.seller join fetch t.buyer where r.id=:reportId
                """;
        Query q = em.createQuery(sql, Report.class);
        q.setParameter("reportId", reportId);
        return (Report) q.getSingleResult();
    }

    // report 테이블의 총 행 개수 반환 메서드
    public Integer findReportCount(String divide) {
        String query = """
                select count(*) from report_tb
            """;

        if (divide.equals("waiting")) {
            query += "where status=0";
        }

        Query q = em.createNativeQuery(query);

        // Native Query는 기본적으로 Long 타입을 반환하므로 이를 Integer로 변환합니다.
        return ((Number) q.getSingleResult()).intValue();
    }
}