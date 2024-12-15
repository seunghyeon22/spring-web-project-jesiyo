package com.metacoding.web_project.transaction;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.bid.BidResponse;
import com.metacoding.web_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/admin/auction-complete")
    public String auctionComplete(Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String page) {
        List<TransactionResponse.TransactionDTO> dtoList = transactionService.findTransactionTBAndUser(divide, search, page);
        Integer rowCount = transactionService.findTransactionsCount(divide, search);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 10));
        model.addAttribute("model", dtoList);
        model.addAttribute("divide", divide);
        model.addAttribute("search", search);
        return "admin/auction-complete-admin";
    }

    // 낙찰된 물품(판매) 화면 열기 - 판매 확정 누름, 안 누름 전부 포함
    @GetMapping("/s/mypage-complete-auction")
    public String completeAuction(@AuthenticationPrincipal User user, Model model, @RequestParam(defaultValue = "") String page) {
        List<TransactionResponse.CompleteAuctionDTO> completeAuctionList = transactionService.completeAuctionList(user.getId(), page);
        Integer rowCount = transactionService.totalCompleteAuctionListCount(user.getId());
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 5));
        model.addAttribute("models", completeAuctionList);
        return "complete-auction";
    }

    // 낙찰된 물품(판매) 송장 번호 등록 -> transaction_tb 테이블의 delivery_number update
    @PostMapping("/s/deliveryNumber/update")
    public String deliveryNumUpdate(TransactionRequest.UpdateDeliveryNumberDTO updateDeliveryNumberDTO) {
        transactionService.updateDeliveryNumber(updateDeliveryNumberDTO);
        return "redirect:/s/myPage-complete-auction";
    }

    // 낙찰된 물품(판매) 판매 확정하기 -> transaction_tb 테이블의 seller_status = 1로 update
//    @PostMapping("/s/sellerStatus/update")
//    public String deliveryNumUpdate(TransactionRequest.UpdateDeliveryNumberDTO updateDeliveryNumberDTO) {
//        transactionService.updateDeliveryNumber(updateDeliveryNumberDTO);
//        return "redirect:/mypage-complete-auction";
//    }

    // 낙찰된 물품(판매) 판매 확정하기 -> transaction_tb 테이블의 seller_status = 1로 update
    @PostMapping("/s/sellerstatus/update")
    @ResponseBody
    public ResponseEntity<?> updateSellerStatus(@RequestBody TransactionRequest.UpdateSellerStatusDTO updateSellerStatusDTO) {
        transactionService.updateSellerStatus(updateSellerStatusDTO);

        CommonResp resp = new CommonResp(true, "판매 확정 되었습니다.", null);
        return ResponseEntity.ok(resp);    
    }

    // 낙찰된 물품(판매) 판매 취소하기 -> transaction_tb 테이블의 transaction_status = 1로 update
    @PostMapping("/s/transactionStatusForSeller/update")
    @ResponseBody
    public ResponseEntity<?> updateTransactionStatusForSeller(@RequestBody TransactionRequest.UpdateTransactionStatusForSellerDTO updateTransactionStatusForSellerDTO) {
        transactionService.updateTransactionStatusForSeller(updateTransactionStatusForSellerDTO);

        CommonResp resp = new CommonResp(true, "판매 취소 되었습니다.", null);
        return ResponseEntity.ok(resp);
    }

    // 낙찰된 물품(구매) 화면 열기 - 구매 확정 누름, 안 누름 전부 포함
    @GetMapping("/s/mypage-participated-auction")
    public String participatedAuction(Model model) {
        List<TransactionResponse.ParticipatedAuctionDTO> participatedAuctionList = transactionService.participatedAuctionList();
        model.addAttribute("models", participatedAuctionList);
        return "participated-auction";
    }

    // 낙찰된 물품(구매) 구매 확정하기 -> transaction_tb 테이블의 buyer_status = 1로 update
    @PostMapping("/s/buyerStatus/update")
    @ResponseBody
    public ResponseEntity<?> updateBuyerStatus(@RequestBody TransactionRequest.UpdateBuyerStatusDTO updateBuyerStatusDTO) {
        transactionService.updateBuyerStatus(updateBuyerStatusDTO);

        CommonResp resp = new CommonResp(true, "구매 확정 되었습니다.", null);
        return ResponseEntity.ok(resp);
    }

    // 낙찰된 물품(구매) 구매 취소하기 -> transaction_tb 테이블의 transaction_status = 1로 update
    @PostMapping("/s/transactionStatusForBuyer/update")
    @ResponseBody
    public ResponseEntity<?> updateTransactionStatusForBuyer(@RequestBody TransactionRequest.UpdateTransactionStatusForBuyerDTO updateTransactionStatusForBuyerDTO) {
        transactionService.updateTransactionStatusForBuyer(updateTransactionStatusForBuyerDTO);

        CommonResp resp = new CommonResp(true, "구매 취소 되었습니다.", null);
        return ResponseEntity.ok(resp);
    }

    // 경매 종료 시 transaction_tb에 경매 완료된 데이터 저장
    @PostMapping("/goods-detail/saveTransaction")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequest.SaveDTO saveDTO){
        transactionService.save(saveDTO);

        CommonResp resp = new CommonResp(true, "최종낙찰정보 저장 완료", null);
        return ResponseEntity.ok(resp);
    }
}