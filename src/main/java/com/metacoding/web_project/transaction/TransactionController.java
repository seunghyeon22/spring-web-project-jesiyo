package com.metacoding.web_project.transaction;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.bid.BidResponse;
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
public class TransactionController {
    private final TransactionService transactionService;

    // 로그인 구현 시 경로를 /admin/auction-complete 로 변경 예정
    @GetMapping("/auction-complete")
    public String auctionComplete(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String page) {
        List<TransactionResponse.TransactionDTO> dtoList = transactionService.findTransactionTBAndUser(divide, search, page);
        Integer rowCount = transactionService.findTransactionsCount(divide, search);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount));
        model.addAttribute("model", dtoList);
        model.addAttribute("divide", divide);
        model.addAttribute("search", search);
        return "admin/auction-complete-admin";
    }

    // 낙찰된 물품(판매) 화면 열기 - 판매 확정 안 누름
    @GetMapping("/myPage-complete-auction")
    public String completeAuction(Model model) {
        List<TransactionResponse.CompleteAuctionDTO> completeAuctionList = transactionService.completeAuctionList();
        model.addAttribute("models", completeAuctionList);
        return "complete-auction";
    }

    // 낙찰된 물품(구매) 화면 열기 - 구매 완료 / 구매 확정 누름
    @GetMapping("/myPage-participated-auction")
    public String participatedAuction(Model model) {
        List<TransactionResponse.ParticipatedAuctionDTO> participatedAuctionList = transactionService.participatedAuctionList();
        model.addAttribute("models", participatedAuctionList);
        return "participated-auction";
    }


    // 경매 종료 시 transaction_tb에 경매 완료된 데이터 저장
    @PostMapping("/goods-detail/saveTransaction")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequest.SaveDTO saveDTO){
        transactionService.save(saveDTO);

        CommonResp resp = new CommonResp(true, "최종낙찰정보 저장 완료", null);
        return ResponseEntity.ok(resp);
    }
}