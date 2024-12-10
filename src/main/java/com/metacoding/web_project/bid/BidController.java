package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.CommonResp;
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
public class BidController {
    private final BidService bidService;


    // 로그인 구현 시 경로를 /admin/auction-progress 로 변경 예정
    // 경매 중인 물품 페이지 이동 (관리자)
    @GetMapping("/auction-progress")
    public String auctionProgress(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String search) {
        List<BidResponse.BidDTO> dtoList = bidService.findAllBidsAndUser(divide, search);
        model.addAttribute("model", dtoList);
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

    // 경매 시도 금액 데이터 -> DB의 bid_tb 테이블에 insert
    @PostMapping("/catchDetailPageData")
    public ResponseEntity<?> uploadBidData(@RequestBody BidRequest.TryBidDTO tryBidDTO) {
        bidService.saveTryPrice(tryBidDTO);

        CommonResp resp = new CommonResp(true, "성공", null);
        return ResponseEntity.ok(resp);
    }
}
