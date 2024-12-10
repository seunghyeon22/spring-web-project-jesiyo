package com.metacoding.web_project.bid;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BidService {
    private final BidRepository bidRepository;

<<<<<<< HEAD
    private final HttpSession session;

    @Transactional
    public void saveTryPrice(BidRequest.TryBidDTO tryBidDTO) {
        String username = (String) session.getAttribute("username");
        // 유저 리포지토리에서 username 통해서 해당 user객체 찾아와야됨
        // 찾아온 객체의 id값을 tryBidDTO에 buyer에 담아야한다.


        // ***다음버전 toEntity userId 유동적으로 받을 수 있게 바꿔야됨
        bidRepository.saveV1(tryBidDTO.toEntity(1));
=======
    // 조건에 따라 다른 where 절을 생성하여 전달하는 메서드 (관리자)
    public List<BidResponse.BidDTO> findAllBidsAndUser(String divide, String search) {
        String query;

        // divide에 따라 조건문 생성
        if (divide.equals("buyer")) {
            query = "where b.buyer.name like '%" + search + "%'";
        } else if (divide.equals("seller")) {
            query = "where g.seller.name like '%" + search + "%'";
        } else {
            query = "where g.title like '%" + search + "%'";
        }
        // 쿼리 실행 및 결과 반환
        List<Bid> bidList = bidRepository.findAllBidsJoinAnotherInfo(query);
        List<BidResponse.BidDTO> dtoList = new ArrayList<>();

        for (Bid bid : bidList) {
            dtoList.add(new BidResponse.BidDTO(bid));
        }
        return dtoList;
>>>>>>> 2bdb5af ([feat] 관리자 거래중 페이지 기능 구현 완료)
    }
}

