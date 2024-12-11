package com.metacoding.web_project.transaction;

import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.bid.BidRepository;
import com.metacoding.web_project.bid.BidResponse;
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

    public List<TransactionResponse.TransactionDTO> findAllTransactionTBAndUser(String divide, String search) {
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
        List<Transaction> transactionList = transactionRepository.findAllTransactionJoinAnotherInfo(query);
        List<TransactionResponse.TransactionDTO> dtoList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            dtoList.add(new TransactionResponse.TransactionDTO(transaction));
        }
        return dtoList;
    }
}
