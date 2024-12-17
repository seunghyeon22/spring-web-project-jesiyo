package com.metacoding.web_project.report;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 신고 창 모달에서 신고처리 했을 때 (관리자)
    @PostMapping("/admin/transaction/update")
    public String updateReport(Model model, @RequestParam(defaultValue = "") String reportId, @RequestParam(defaultValue = "") String method) {
        reportService.reportTreatment(reportId, method);
        return "redirect:/admin/confirm-report";
    }
    
    // 판매자, 구매자 신고하기
    @PostMapping("/api/v1/report")
    @ResponseBody
    public ResponseEntity<?> reportBuyer(@AuthenticationPrincipal User user, @RequestBody ReportRequest.ReportSaveDTO reportSaveDTO) {
        reportService.save(user.getId(),reportSaveDTO);
        CommonResp resp = new CommonResp(true, "신고 성공", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}