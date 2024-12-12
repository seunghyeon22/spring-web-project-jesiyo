package com.metacoding.web_project.report;

import com.metacoding.web_project._core.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;

    // 로그인 구현 시 경로를 /admin/confirm-report 로 변경 예정
    @GetMapping("/confirm-report")
    public String confirmReport(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String page) {
        List<ReportResponse.ReportDTO> dtoList = reportService.findReportJoinAnotherInfo(divide, page); // 조건에 따라 알맞은 데이터 반환
        Integer rowCount = reportService.findReportCount(divide); // 행의 총 개수 반환
        model.addAttribute("model", dtoList);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount));
        model.addAttribute("status", divide);
        return "admin/confirm-report";
    }

    @PostMapping("/transaction/update")
    public String updateReport(Model model, @RequestParam(defaultValue = "") String reportId, @RequestParam(defaultValue = "") String method) {
        reportService.reportTreatment(reportId, method);
        return "redirect:/confirm-report";
    }

}
