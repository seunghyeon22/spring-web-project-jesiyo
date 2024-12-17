package com.metacoding.web_project.recode;

import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.bid.Bid;
import com.metacoding.web_project.bid.BidRepository;
import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    // 입찰 실패한 상품을 가져오고, 상품 id를 통해 성공한 입찰 이력의 금액을 함께 가져와 DTO로 변환한 뒤 반환
    @Transactional
    public List<RecodeResponse.ParticipateFailDTO> findFailRecodeByUserId(Integer id, String divide, String search, String page) {
        // 한페이지에 출력되는 DTO base 값
        List<Recode> userParticipateFailRecodeList = recodeRepository.findFailRecodeByUserId(divide, search, id, PageUtil.offsetCount(page, 10), 10);

        List<Integer> goodsIdList = new ArrayList<>();
        for (Recode recode : userParticipateFailRecodeList) {
            goodsIdList.add(recode.getGoods().getId());
        }
        List<Recode> successRecodeList = recodeRepository.findSuccessPrice(goodsIdList);
        List<RecodeResponse.ParticipateFailDTO> dtoList = new ArrayList<>();

        for (Recode successRecode : successRecodeList) {
            for (Recode failList : userParticipateFailRecodeList) {
                if (successRecode.getGoods().getId().equals(failList.getGoods().getId())) {
                    dtoList.add(new RecodeResponse.ParticipateFailDTO(failList, successRecode.getTryPrice()));
                    break;
                }
            }
        }

        return dtoList;
    }

    // 유저 id 기준 입찰 실패한 기록들의 총 개수 반환 메서드
    public Integer countFailedRecodeByUserId(Integer userId, String divide, String search) {
        return recodeRepository.countFailedRecodeByUserId(userId, divide, search);
    }

}
