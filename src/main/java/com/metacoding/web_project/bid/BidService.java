package com.metacoding.web_project.bid;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BidService {
    private final BidRepository bidRepository;

    private final HttpSession session;

    @Transactional
    public void saveTryPrice(BidRequest.TryBidDTO tryBidDTO) {
        String username = (String) session.getAttribute("username");
        // 유저 리포지토리에서 username 통해서 해당 user객체 찾아와야됨
        // 찾아온 객체의 id값을 tryBidDTO에 buyer에 담아야한다.


        // ***다음버전 toEntity userId 유동적으로 받을 수 있게 바꿔야됨
        bidRepository.saveV1(tryBidDTO.toEntity(1));
    }
}
