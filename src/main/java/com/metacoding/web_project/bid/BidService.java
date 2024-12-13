package com.metacoding.web_project.bid;

import com.metacoding.web_project._core.error.ex.Exception400;
import com.metacoding.web_project.user.User;
import com.metacoding.web_project.user.UserRepository;
import com.metacoding.web_project.goods.Goods;
import com.metacoding.web_project.goods.GoodsRepository;
import com.metacoding.web_project.recode.Recode;
import com.metacoding.web_project.recode.RecodeRepository;
import com.metacoding.web_project.recode.RecodeRepositoryInterface;
import com.metacoding.web_project.transaction.Transaction;
import com.metacoding.web_project.transaction.TransactionRepository;
import com.metacoding.web_project.useraccount.UserAccount;
import com.metacoding.web_project.useraccount.UserAccountRepository;
import com.metacoding.web_project._core.util.PageUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class BidService {
    private final BidRepository bidRepository;

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final RecodeRepository recodeRepository;
    private final RecodeRepositoryInterface recodeRepositoryInterfase;
    private final GoodsRepository goodsRepository;
    private final TransactionRepository transactionRepository;
    private final HttpSession session;

    @Transactional
    public void saveTryPrice(BidRequest.TryBidDTO tryBidDTO, String username) {

        User user = userRepository.findByUsername(username);

        Integer goodsId = tryBidDTO.getGoodsId();
        Integer tryPrice = tryBidDTO.getTryPrice();

        Optional<Bid> highestBid = bidRepository.findByGoodsIdDescWithLock(goodsId);

        if (highestBid.isPresent()) {
            Integer currentHighestPrice = highestBid.get().getTryPrice();
            if (tryPrice <= currentHighestPrice) {
                throw new Exception400("입찰금액이 현재 최고 입찰 금액보다 높아야합니다.");
            }
        }

        // ***다음버전 toEntity userId 유동적으로 받을 수 있게 바꿔야됨
        bidRepository.saveV1(tryBidDTO.toEntity(1));
    }


    // 조건에 따라 다른 where 절을 생성하여 전달하는 메서드 (관리자)
    public List<BidResponse.BidDTO> findBidsAndUser(String divide, String search, String page) {
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
        List<Bid> bidList = bidRepository.findBidsJoinAnotherInfo(query, PageUtil.offsetCount(page, 10), 10);
        List<BidResponse.BidDTO> dtoList = new ArrayList<>();

        for (Bid bid : bidList) {
            dtoList.add(new BidResponse.BidDTO(bid));
        }
        return dtoList;
    }

    // 조건에 맞는 bid 테이블 행의 총 개수를 구하는 메서드
    public Integer findBidsCount(String divide, String search) {
        String query;

        // divide에 따라 조건문 생성
        if (divide.equals("buyer")) {
            query = "where b.buyer.name like '%" + search + "%'";
        } else if (divide.equals("seller")) {
            query = "where g.seller.name like '%" + search + "%'";
        } else {
            query = "where g.title like '%" + search + "%'";
        }

        return bidRepository.findBidsCount(query);
    }


    @Transactional
    public void deleteByGoodsId(Integer id) {
        bidRepository.deleteByGoodsId(id);
    }

    // 경매중인 물품(판매) 목록 보기
//    @Transactional // 트랜잭션 범위 내에서 조회하기 위함(지연 로딩 예외 발생 방지)
//    public List<BidResponse.BeingAuctionedDTO> beingAuctionedList(String username) {
//        User user = userRepository.findByUsername(username);
//        // 임시로 buyerId = 1인 경우만 가져옴 로그인과 연결할 때 바꿀것
//        List<Bid> bidList = bidRepository.findByBuyerIdForSell(user.getId());
//
//        // BeingAuctionedDTO로 변환
//        List<BidResponse.BeingAuctionedDTO> beingAuctionedDTOList = new ArrayList<>();
//
//        for (Bid bid : bidList) {
//            beingAuctionedDTOList.add(new BidResponse.BeingAuctionedDTO(bid));
//        }
//        return beingAuctionedDTOList;
//    }

    // 경매 참여중인 물품(구매) 목록 보기
    @Transactional // 트랜잭션 범위 내에서 조회하기 위함(지연 로딩 예외 발생 방지)
    public List<BidResponse.ParticipatingAuctionDTO> participatingAuctionList(Integer id, String page) {

        // 임시로 buyerId = 1인 경우만 가져옴 로그인과 연결할 때 바꿀것
        List<Bid> bidList = bidRepository.findByBuyerIdForBuy(id, PageUtil.offsetCount(page, 5), 5);

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

    public Integer findAllBidCount(Integer userId) {
        return bidRepository.findAllBidCount(userId);
    }

    // 경매 취소
    @Transactional
    public void cancelAuction(Integer goodsId) {
        // 경매 취소할 물품의 경매목록 가져오기
        List<Bid> bids = bidRepository.findAllByGoodsId(goodsId);
        if(bids.isEmpty()) {
            throw  new Exception400("경매 데이터가 없습니다.");
        }

        for(Bid bid : bids) {
            // 경매에 참여한 입찰자들의 돈을 반환
            UserAccount ua = userAccountRepository.findById(bid.getBuyer().getId());
            ua.updateUserInfo(bid.getTryPrice());
        }
        // 경매에 참여한 데이터들을 recode 테이블로 옮김
        List<Recode> recode = IntStream.range(0,bids.size()).mapToObj(i->
             Recode.builder().
                    buyer(bids.get(i).getBuyer()).
                    goods(bids.get(i).getGoods()).
                    tryPrice(bids.get(i).getTryPrice()).
                    successStatus(0).
                    createdAt(bids.get(i).getCreatedAt()).
                    build()).collect(Collectors.toList());
        recodeRepositoryInterfase.saveAll(recode);

        //물품에 대한 경매에 참여중인 데이터 삭제
        bidRepository.deleteByGoodsId(goodsId);
        //good에 있는 물품의 상태를 변경 - 3 : 경매 취소
        Optional<Goods> goods = goodsRepository.findById(goodsId);
        goods.get().cancelAuction();
    }
    // 조기 종료 - 물품의 status를 변경
    @Transactional
    public void endEarlyAuction1(Integer goodsId) {
        Optional<Goods> goods = goodsRepository.findById(goodsId);
        goods.get().endAuction();
    }
    // 조기 종료 - part2
    @Transactional
    public void endEarlyAuction2(Integer goodsId) {
        // 판매자를 확인
        Goods goods = goodsRepository.findById(goodsId).get();
        // 종료하는 물품의 입찰 정보를 모두 불러옴
        List<Bid> bids = bidRepository.findAllByGoodsId(goodsId);
        // 최고 경매
        Bid bid = bids.get(bids.size() - 1);
        // 낙찰 테이블 등록
        transactionRepository.save(Transaction.builder().goods(goods).
               buyer(bid.getBuyer()).
               seller(goods.getSeller()).
               buyerStatus(0).
               sellerStatus(0).
               transactionStatus(0).
               successPrice(bid.getTryPrice()).
               build());

       for(int i=0; i<bids.size()-1; i++) {
           // 최고가를 제외한 나머지 입찰금액 반환
           UserAccount ua = userAccountRepository.findById(bids.get(i).getBuyer().getId());
           ua.updateUserInfo(bids.get(i).getTryPrice());
        }
        // 경매에 참여한 데이터들을 recode 테이블로 옮김
        List<Recode> recode = IntStream.range(0,bids.size()).mapToObj(i->{
            int successStatus = (i == bids.size()-1)?1:0;
                return Recode.builder().
                buyer(bids.get(i).getBuyer()).
                goods(bids.get(i).getGoods()).
                tryPrice(bids.get(i).getTryPrice()).
                successStatus(successStatus).
                createdAt(bids.get(i).getCreatedAt()).
                build();
        }).collect(Collectors.toList());
        // 한번에 insert
        recodeRepositoryInterfase.saveAll(recode);
        // bid에 있는 데이터 삭제
        bidRepository.deleteByGoodsId(goodsId);
    }

    @Transactional
    public void cancelBid(BidRequest.CancelBidDTO dto){
        bidRepository.deleteBid(dto.getGoodsId(),dto.getUserId());
        UserAccount ua = userAccountRepository.findById(dto.getUserId());
        ua.updateUserInfo(dto.getTryPrice());
    }

}