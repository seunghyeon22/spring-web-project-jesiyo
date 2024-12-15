package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project.user.User;
import jakarta.servlet.http.HttpSession;
import com.metacoding.web_project._core.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BidController {
    private final BidService bidService;

    private final HttpSession session;

    // 경매 중인 물품 페이지 이동 (관리자)
    @GetMapping("/admin/auction-progress")
    public String auctionProgress(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String page) {
        List<BidResponse.BidDTO> dtoList = bidService.findBidsAndUser(divide, search, page);
        Integer rowCount = bidService.findBidsCount(divide, search);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 10));
        model.addAttribute("model", dtoList);
        model.addAttribute("divide", divide);
        model.addAttribute("search", search);

        return "admin/auction-progress-admin";
    }

    // 경매 참여 중인 물품(구매) 화면 열기
    @GetMapping("/s/mypage-participating-auction")
    public String participatingAuction(@AuthenticationPrincipal User user, Model model, @RequestParam(defaultValue = "") String page) {
        List<BidResponse.ParticipatingAuctionDTO> participatingAuctionList = bidService.participatingAuctionList(user.getId(), page);
        Integer rowCount = bidService.findAllBidCount(user.getId());
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 3));
        model.addAttribute("models", participatingAuctionList);
        return "participating-auction";
    }

    // 경매 시도 금액 데이터 -> DB의 bid_tb 테이블에 insert
    @PostMapping("/s/catchDetailPageData")
    public ResponseEntity<?> uploadBidData(@AuthenticationPrincipal User user, @RequestBody BidRequest.TryBidDTO tryBidDTO) {
        String username = (String) session.getAttribute("username");
        bidService.saveTryPrice(tryBidDTO,user.getId());

        CommonResp resp = new CommonResp(true, "성공", null);
        return ResponseEntity.ok(resp);
    }

    // 경매가 끝난 물건 경매기록 삭제 (recode_tb에 옮긴 후 실행됩니다.)
    @PostMapping("/goods-detail/endBid/{id}/delete")
    public ResponseEntity<?> deleteBid(@PathVariable("id") Integer id) {
        bidService.deleteByGoodsId(id);
        return ResponseEntity.ok(CommonResp.success(null));
    }

    // 경매 취소 버튼
    @DeleteMapping("/api/v1/bid-delete/{goodsId}")
    @ResponseBody
    public ResponseEntity<?> deleteBidsByGoods(@PathVariable("goodsId") Integer goodsId) {
        bidService.cancelAuction(goodsId);
        CommonResp resp = new CommonResp(true,"성공",null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    //경매 조기 종료
    @PostMapping("/api/v1/early-transaction")
    @ResponseBody
    public ResponseEntity<?> endEarlyAuctionGoods(@RequestBody Integer goodsId) {
        boolean endEarlyAuctionAvailable = bidService.endEarlyAuction1(goodsId);
        if (endEarlyAuctionAvailable) {
            bidService.endEarlyAuction2(goodsId);
            CommonResp resp = new CommonResp(true, "성공", null);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        CommonResp resp = new CommonResp(false, "실패", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    //입찰 취소
    @PostMapping("/api/v1/cancelBid")
    @ResponseBody
    public ResponseEntity<?> cancelBid(@RequestBody BidRequest.CancelBidDTO cancelBidDTO) {
        bidService.cancelBid(cancelBidDTO);
        CommonResp resp = new CommonResp(true,"성공", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    // 재입찰
    @PostMapping("/api/v1/re-bid")
    @ResponseBody
    public ResponseEntity<?> reBid(@AuthenticationPrincipal UserDetails user,@RequestBody BidRequest.ReBidRequestDTO dto) {
        bidService.reBid(user.getUsername(),dto);
        CommonResp resp = new CommonResp(true,"성공", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
