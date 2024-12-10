package com.metacoding.web_project.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;

    // 로그인 구현 시 경로를 /admin/auction-complete 로 변경 예정
    @GetMapping("/auction-complete")
    public String auctionComplete() {
        return "admin/auction-complete-admin";
    }

    // 낙찰된 물품(판매) 화면 열기 - 경매 완료 / 구매 확정 안 누름
    @GetMapping("/myPage-complete-auction")
    public String completeAuction(Model model) {
        return "complete-auction";
    }

    // 낙찰된 물품(구매) 화면 열기 - 구매 완료 / 구매 확정 누름
    @GetMapping("/myPage-participated-auction")
    public String participatedAuction(Model model) {
        return "participated-auction";
    }
}