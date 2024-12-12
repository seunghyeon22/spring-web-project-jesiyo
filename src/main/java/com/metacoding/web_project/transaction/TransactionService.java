package com.metacoding.web_project.transaction;

import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.bid.BidRepository;
import com.metacoding.web_project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final BidRepository bidRepository;

    @Transactional
    public void save(TransactionRequest.SaveDTO saveDTO) {

        Integer tryPrice = saveDTO.getSuccessPrice();
        Optional<Bid> result = bidRepository.findByTryPrice(tryPrice);
        Bid bid = result.get();

        User buyer = bid.getBuyer();

        transactionRepository.save(saveDTO.toEntity(buyer.getId()));
    }

    // 조건에 따라 최대 10개의 transaction 행을 유저 정보와 함께 가져오는 메서드 (관리자)
    public List<TransactionResponse.TransactionDTO> findTransactionTBAndUser(String divide, String search, String page) {
        String query;

        // divide에 따라 조건문 생성
        if (divide.equals("buyer")) {
            query = "where t.buyer.name like '%" + search + "%'";
        } else if (divide.equals("seller")) {
            query = "where t.seller.name like '%" + search + "%'";
        } else {
            query = "where t.goods.title like '%" + search + "%'";
        }

        // 쿼리 실행 및 결과 반환
        List<Transaction> transactionList = transactionRepository.findTransactionJoinAnotherInfo(query, PageUtil.offsetCount(page, 10), 10);
        List<TransactionResponse.TransactionDTO> dtoList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            dtoList.add(new TransactionResponse.TransactionDTO(transaction));
        }
        return dtoList;
    }

    // 조건에 맞는 transaction 테이블 행의 총 개수를 구하는 메서드
    public Integer findTransactionsCount(String divide, String search) {
        String query;
        // divide에 따라 조건문 생성
        if (divide.equals("buyer")) {
            query = " where t.buyer.name like '%" + search + "%'";
        } else if (divide.equals("seller")) {
            query = " where t.seller.name like '%" + search + "%'";
        } else {
            query = " where t.goods.title like '%" + search + "%'";
        }
        return transactionRepository.findTransactionsCount(query);
    }

    // 낙찰된 물품(판매) 화면 열기 - 판매 확정 안 누름
    @Transactional
    public List<TransactionResponse.CompleteAuctionDTO> completeAuctionList() {
        
        // 임시로 sellerId = 1인 경우만 가져옴, 로그인과 연결할 때 바꿀 것
        List<Transaction> transactionList = transactionRepository.findBySellerIdNotConfirmOfSell(1);

        // completeAuctionDTO로 변환
        List<TransactionResponse.CompleteAuctionDTO> completeAuctionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            completeAuctionDTOList.add(new TransactionResponse.CompleteAuctionDTO(transaction));
        }
        return completeAuctionDTOList;
    }

    // 낙찰된 물품(구매) 화면 열기 - 구매 확정 누름, 안 누름 다 포함
    @Transactional
    public List<TransactionResponse.ParticipatedAuctionDTO> participatedAuctionList() {

        // 임시로 buyerId = 1인 경우만 가져옴, 로그인과 연결할 때 바꿀 것
        List<Transaction> transactionList = transactionRepository.findByBuyerIdForAllBuy(1);

        // ParticipatedAuctionDTO로 변환
        List<TransactionResponse.ParticipatedAuctionDTO> participatedAuctionDTOList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            participatedAuctionDTOList.add(new TransactionResponse.ParticipatedAuctionDTO(transaction));
        }
        return participatedAuctionDTOList;
    }
}
