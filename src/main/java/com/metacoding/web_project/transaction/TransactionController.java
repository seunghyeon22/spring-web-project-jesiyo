package com.metacoding.web_project.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
}
