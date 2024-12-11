package com.metacoding.web_project.report;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;

    // 로그인 구현 시 경로를 /admin/confirm-report 로 변경 예정
    @GetMapping("/confirm-report")
    public String confirmReport(Model model, @RequestParam(defaultValue = "") String divide) {
        List<ReportResponse.ReportDTO> dtoList = reportService.findReportJoinAnotherInfo(divide);
        model.addAttribute("model", dtoList);
        model.addAttribute("status", divide);
        return "admin/confirm-report";
    }
}
