package com.metacoding.web_project.recode;

import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.bid.BidRepository;
import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecodeService {
    private final RecodeRepository recodeRepository;

    private final BidRepository bidRepository;

    private final TransactionRepository transactionRepository;

    @Transactional
    public void save(Integer id) {
        List<Bid> list = bidRepository.findAllByGoodsId(id);

        Optional<Transaction> result = transactionRepository.findSuccessBuyerByGoodsId(id);
        Transaction transaction = result.get();
        Integer buyerId = transaction.getBuyer().getId();

        for (Bid bid : list) {
            RecodeRequest.saveDTO recodeDto = new RecodeRequest.saveDTO();

            // 성공 유저인지 확인
            // status(경매성공, 실패) 여부는 recode가 생성될 때
            // 해당 유저가 경매에 성공,실패를 판단해야하기 때문에 setter을 사용했습니다.
            if (bid.getBuyer().getId().equals(buyerId)) {
                recodeDto.setSuccessStatus(1); // 경매 성공 유저
            } else {
                recodeDto.setSuccessStatus(2); // 경매 실패 유저
            }

            recodeRepository.save(recodeDto.toEntity(bid));
        }

    }
}
