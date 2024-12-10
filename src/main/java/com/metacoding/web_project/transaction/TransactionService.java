package com.metacoding.web_project.transaction;

import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.bid.BidRepository;
import com.metacoding.web_project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
