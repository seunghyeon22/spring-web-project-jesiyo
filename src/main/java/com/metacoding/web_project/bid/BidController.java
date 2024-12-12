package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.CommonResp;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BidController {
    private final BidService bidService;

    private final HttpSession session;

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
        List<BidResponse.BeingAuctionedDTO> beingAuctionedList = bidService.beingAuctionedList();
        model.addAttribute("models", beingAuctionedList);
        return "being-auctioned";
    }

    // 경매 참여 중인 물품(구매) 화면 열기
    @GetMapping("/myPage-participating-auction")
    public String participatingAuction(Model model) {
        List<BidResponse.ParticipatingAuctionDTO> participatingAuctionList = bidService.participatingAuctionList();
        model.addAttribute("models", participatingAuctionList);
        return "participating-auction";
    }

    // 경매 시도 금액 데이터 -> DB의 bid_tb 테이블에 insert
    @PostMapping("/catchDetailPageData")
    public ResponseEntity<?> uploadBidData(@RequestBody BidRequest.TryBidDTO tryBidDTO) {
        String username = (String) session.getAttribute("username");
        bidService.saveTryPrice(tryBidDTO,username);

        CommonResp resp = new CommonResp(true, "성공", null);
        return ResponseEntity.ok(resp);
    }

    // 경매가 끝난 물건 경매기록 삭제 (recode_tb에 옮긴 후 실행됩니다.)
    @PostMapping("/goods-detail/endBid/{id}/delete")
    public void deleteBid(@PathVariable int id) {

        bidService.deleteByGoodsId(id);
        return;
    }

    // 경매 취소 버튼
    @DeleteMapping("/api/v1/bid-delete/{goodsId}")
    @ResponseBody
    public ResponseEntity<?> deleteBidsByGoods(@PathVariable("goodsId") Integer goodsId) {
        bidService.cancelAuction(goodsId);
        CommonResp resp = new CommonResp(true,"성공",null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
