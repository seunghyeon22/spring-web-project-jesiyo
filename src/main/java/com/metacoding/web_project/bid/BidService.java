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

    private final HttpSession session;

    @Transactional
    public void saveTryPrice(BidRequest.TryBidDTO tryBidDTO) {
        String username = (String) session.getAttribute("username");
        // 유저 리포지토리에서 username 통해서 해당 user객체 찾아와야됨
        // 찾아온 객체의 id값을 tryBidDTO에 buyer에 담아야한다.


        // ***다음버전 toEntity userId 유동적으로 받을 수 있게 바꿔야됨
        bidRepository.saveV1(tryBidDTO.toEntity(1));
    }


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
    }

    public void deleteByGoodsId(int id) {
        bidRepository.deleteByGoodsId(id);
    }

    // 경매중인 물품(판매) 목록 보기
    @Transactional // 트랜잭션 범위 내에서 조회하기 위함(지연 로딩 예외 발생 방지)
    public List<BidResponse.BeingAuctionedDTO> beingAuctionedList() {

        // 임시로 buyerId = 1인 경우만 가져옴 로그인과 연결할 때 바꿀것 
        List<Bid> bidList = bidRepository.findByBuyerIdForSell(2);
        
        // BeingAuctionedDTO로 변환
        List<BidResponse.BeingAuctionedDTO> beingAuctionedDTOList = new ArrayList<>();

        for (Bid bid : bidList) {
            beingAuctionedDTOList.add(new BidResponse.BeingAuctionedDTO(bid));
        }
        return beingAuctionedDTOList;
    }

    // 경매 참여중인 물품(구매) 목록 보기
    @Transactional // 트랜잭션 범위 내에서 조회하기 위함(지연 로딩 예외 발생 방지)
    public List<BidResponse.ParticipatingAuctionDTO> participatingAuctionList() {

        // 임시로 buyerId = 1인 경우만 가져옴 로그인과 연결할 때 바꿀것
        List<Bid> bidList = bidRepository.findByBuyerIdForBuy(1);

        // ParticipatingAuctionDTO로 변환
        List<BidResponse.ParticipatingAuctionDTO> participatingAuctionDtoList = new ArrayList<>();

        for (Bid bid : bidList) {
            // 최고 입찰가 조회
            Bid bestPrice = bidRepository.findMaxPrice(bid.getGoods().getId());
            Integer maxPrice = null;
            if (bestPrice != null) {
                maxPrice = bestPrice.getTryPrice();
            }

            // ParticipatingAuctionDTO 생성
            BidResponse.ParticipatingAuctionDTO participatingAuctionDto = new BidResponse.ParticipatingAuctionDTO(bid);
            participatingAuctionDto.setMaxPrice(maxPrice); // 최고 입찰가 설정
            participatingAuctionDtoList.add(participatingAuctionDto);
        }
        return participatingAuctionDtoList;
    }
}