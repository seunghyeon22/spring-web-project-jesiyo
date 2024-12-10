package com.metacoding.web_project.goods;

import com.metacoding.web_project._core.CommonResp;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class GoodsController {
    private final GoodsService goodsService;


    private final HttpSession session;

    // 제품 상세페이지 불러오기
    @GetMapping("/goods-detail/{id}")
    public String goodsDetail(@PathVariable("id")Integer id, Model model) {

        // ***임시*** // 세션에 username 저장
        String username = "ssar";
        session.setAttribute("username", username);

        GoodsResponse.GoodsDetailDTO goodsDetailDTO = goodsService.getGoodsInfo(id);
        model.addAttribute("goods", goodsDetailDTO);

        return "goods-detail";
    }

    // 제품 등록 화면 열기
    @GetMapping("/myPage-goods-register")
    public String register() {
        return "goods-register";
    }

    // 제품 등록
    @PostMapping("/goods/save")
    public String goodsSave(GoodsRequest.GoodsSaveDTO goodsSaveDTO) {
        System.out.println(goodsSaveDTO.getEndAt());
        System.out.println(goodsSaveDTO.getTitle());
        goodsService.goodsSave(goodsSaveDTO);
        return "redirect:/myPage-being-auctioned"; // 경매중인물품 리스트 화면으로 리다이렉트
    }

    // 경매시간 종료 상품 상태 변경
    @PostMapping("/goods-detail/goodsStatus/update")
    public ResponseEntity<?> updateStatus(@RequestBody GoodsRequest.GoodsStatusDTO goodsStatusDTO){

        //goodsService에 status 변경기능 위임
        goodsService.updateGoodsStatus(goodsStatusDTO);

        CommonResp resp = new CommonResp(true, "데이터 변경 성공", null);
        return ResponseEntity.ok(resp);
    }
}
