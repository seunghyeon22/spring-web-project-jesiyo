package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project.user.User;
import jakarta.servlet.http.HttpSession;
import com.metacoding.web_project._core.util.PageUtil;
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

    // 경매 중인 물품(판매) 화면 열기
    @GetMapping("/s/myPage-being-auctioned")
    public String beingAuctioned(Model model) {
        List<BidResponse.BeingAuctionedDTO> beingAuctionedList = bidService.beingAuctionedList();
        model.addAttribute("models", beingAuctionedList);
        return "being-auctioned";
    }

    // 경매 참여 중인 물품(구매) 화면 열기
    @GetMapping("/s/myPage-participating-auction")
    public String participatingAuction(@AuthenticationPrincipal User user, Model model, @RequestParam(defaultValue = "") String page) {
        List<BidResponse.ParticipatingAuctionDTO> participatingAuctionList = bidService.participatingAuctionList(user.getId(), page);
        Integer rowCount = bidService.findAllBidCount(user.getId());
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 5));
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
    public ResponseEntity<?> deleteBid(@PathVariable("id") Integer id) {
        System.out.println("요청왔음 받은 아이디: " + id);
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
        bidService.endEarlyAuction1(goodsId);
        bidService.endEarlyAuction2(goodsId);
        CommonResp resp = new CommonResp(true, "성공", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    //입찰 취소
    @PostMapping("/api/v1/cancelBid")
    @ResponseBody
    public ResponseEntity<?> cancelBid(@RequestBody BidRequest.CancelBidDTO cancelBidDTO) {
        System.out.println(cancelBidDTO.getGoodsId());
        System.out.println(cancelBidDTO.getUserId());
        System.out.println(cancelBidDTO.getTryPrice());
        bidService.cancelBid(cancelBidDTO);
        CommonResp resp = new CommonResp(true,"성공", null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
