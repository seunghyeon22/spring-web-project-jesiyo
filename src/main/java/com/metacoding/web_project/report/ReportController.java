package com.metacoding.web_project.report;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;

    // 신고 관련 관리자 페이지
    @GetMapping("/admin/confirm-report")
    public String confirmReport(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String page) {
        List<ReportResponse.ReportDTO> dtoList = reportService.findReportJoinAnotherInfo(divide, page); // 조건에 따라 알맞은 데이터 반환
        Integer rowCount = reportService.findReportCount(divide); // 행의 총 개수 반환
        model.addAttribute("model", dtoList);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 10));
        model.addAttribute("status", divide);
        return "admin/confirm-report";
    }

    // 신고 창 모달에서 경매취소했을 때 (관리자)
    @PostMapping("/admin/transaction/update")
    public String updateReport(Model model, @RequestParam(defaultValue = "") String reportId, @RequestParam(defaultValue = "") String method) {
        reportService.reportTreatment(reportId, method);
        return "redirect:/admin/confirm-report";
    }
    
    // 구매자 신고하기
    @PostMapping("/s/report-buyer")
    public ResponseEntity<?> reportBuyer(@RequestBody ReportRequest.ReportSaveDTO reportSaveDTO) {
        reportService.save(reportSaveDTO);

        CommonResp resp = new CommonResp(true, "구매자 신고 성공", null);
        return ResponseEntity.ok(resp);
    }

    // 판매자 신고하기
    @PostMapping("/s/report-seller")
    public ResponseEntity<?> reportSeller(@RequestBody ReportRequest.ReportSaveDTO reportSaveDTO) {
        reportService.save(reportSaveDTO);

        CommonResp resp = new CommonResp(true, "판매자 신고 성공", null);
        return ResponseEntity.ok(resp);
    }
}