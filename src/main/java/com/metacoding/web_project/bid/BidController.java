package com.metacoding.web_project.bid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // 경매 중인 물품(판매) 화면 열기
    @GetMapping("/myPage-being-auctioned")
    public String beingAuctioned(Model model) {
        return "being-auctioned";
    }

    // 경매 참여 중인 물품(구매) 화면 열기
    @GetMapping("/myPage-participating-auction")
    public String participatingAuction(Model model) {
        return "participating-auction";
    }
}
