package com.metacoding.web_project.report;

import com.metacoding.web_project._core.error.ex.Exception400;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.transaction.TransactionRepository;
import com.metacoding.web_project.useraccount.UserAccount;
import com.metacoding.web_project.useraccount.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserAccountRepository userAccountRepository;
    private final TransactionRepository transactionRepository;

    // 신고 관련 테이블과 다른 테이블들을 join 한 결과를 받아 DTO로 변환한 뒤 반환하는 메서드 (관리자)
    public List<ReportResponse.ReportDTO> findReportJoinAnotherInfo(String divide, String page) {
        String query = "";

        // divide에 따라 조건문 생성
        if ((divide.equals("waiting")) ) {
            query = "where r.status = 0";
        }
        // 조건문을 전달한 쿼리 실행 및 결과 반환
        List<Report> reportList = reportRepository.findReportJoinAnotherInfo(query, PageUtil.offsetCount(page, 10), 10);
        List<ReportResponse.ReportDTO> reportDTOList = new ArrayList<>();

        for (Report report : reportList) {
            reportDTOList.add(new ReportResponse.ReportDTO(report));
        }
        return reportDTOList;
    }

    // report 행의 총 개수를 구하는 메서드
    public Integer findReportCount(String divide) {
        return reportRepository.findReportCount(divide);
    }

    @Transactional
    public void reportTreatment(String reportId, String method) {
        if (reportId.equals("") || method.equals("")) {
            throw new Exception400("잘못된 요청입니다.");
        }
        Report reportPC = reportRepository.findById(reportId);
        Optional<Transaction> transaction = transactionRepository.findById(reportPC.getTransaction().getId());
        if (!transaction.isPresent()) {
            throw new Exception400("잘못된 요청입니다.");
        }

        reportPC.updateStatus(method);
        if (method.equals("cancel")) {
            UserAccount buyerAccount = userAccountRepository.findByUsername(reportPC.getTransaction().getBuyer().getUsername());
            buyerAccount.updateUserInfo(reportPC.getTransaction().getSuccessPrice());
            transaction.get().updateStatus(null, null, 4, null);
        }
        if (method.equals("seller")) {
            UserAccount sellerAccount = userAccountRepository.findByUsername(reportPC.getTransaction().getSeller().getUsername());
            sellerAccount.updateUserInfo(reportPC.getTransaction().getSuccessPrice());
            transaction.get().updateStatus(null, null, 5, null);
        }


    }
    
    // 신고하기
    @Transactional
    public void save(Integer userId, ReportRequest.ReportSaveDTO reportSaveDTO) {
       Optional<Transaction> transaction = transactionRepository.findById(reportSaveDTO.getTransactionId());
       if(transaction.isPresent()) {
           if(transaction.get().getBuyer().getId().equals(userId)){
               Report report = Report.builder().
                       reporter(transaction.get().getBuyer()).
                       reported(transaction.get().getSeller()).
                       reason(reportSaveDTO.getReason()).
                       transaction(transaction.get()).
                       status(0).
                       build();
               reportRepository.save(report);
           }else if(transaction.get().getSeller().getId().equals(userId)){
               Report report = Report.builder().
                      reporter(transaction.get().getSeller()).
                      reported(transaction.get().getBuyer()).
                      reason(reportSaveDTO.getReason()).
                      transaction(transaction.get()).
                      status(0).
                      build();
               reportRepository.save(report);
           }else{
               throw new Exception400("자신이 등록한 물품 혹은 낙찰된 물품이 아닙니다.");
           }
       }

        transaction.get().statusReport(3);
    }
}
