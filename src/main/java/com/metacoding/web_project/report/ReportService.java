package com.metacoding.web_project.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    // 신고 관련 테이블과 다른 테이블들을 join 한 결과를 받아 DTO로 변환한 뒤 반환하는 메서드 (관리자)
    public List<ReportResponse.ReportDTO> findReportJoinAnotherInfo(String divide) {
        String query = "";

        // divide에 따라 조건문 생성
        if ((divide.equals("waiting")) ) {
            query = "where r.status = 0";
        }
        // 조건문을 전달한 쿼리 실행 및 결과 반환
        List<Report> reportList = reportRepository.findReportJoinAnotherInfo(query);
        List<ReportResponse.ReportDTO> reportDTOList = new ArrayList<>();

        for (Report report : reportList) {
            reportDTOList.add(new ReportResponse.ReportDTO(report));
        }
        return reportDTOList;
    }
}
