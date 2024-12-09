package com.metacoding.web_project.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BidController {
    private final BidService bidService;


    // 로그인 구현 시 경로를 /admin/auction-progress 로 변경 예정
    @GetMapping("/auction-progress")
    public String auctionProgress() {
        return "admin/auction-progress-admin";
    }
}
