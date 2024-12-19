package com.metacoding.web_project.goods;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.category.CategoryResponse;
import com.metacoding.web_project.category.CategoryService;
import com.metacoding.web_project.user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GoodsController {
    private final GoodsService goodsService;
    private final CategoryService categoryService;

    private final HttpSession session;

    // 제품 상세페이지 불러오기
    @GetMapping("/goods-detail/{id}")
    public String goodsDetail(@PathVariable("id")Integer id, Model model) {

        GoodsResponse.GoodsDetailDTO goodsDetailDTO = goodsService.getGoodsInfo(id);
        model.addAttribute("goods", goodsDetailDTO);

        return "goods-detail";
    }

    // 제품 등록 화면 열기
    @GetMapping("/s/mypage-goods-register")
    public String register(@AuthenticationPrincipal User user, Model model) {
        List<CategoryResponse.CategoryDTO> allCategory = categoryService.findAllCategory(null);
        model.addAttribute("model", allCategory);
        model.addAttribute("sellerId", user.getId());
        return "goods-register";
    }

    // 제품 등록
    @PostMapping("/s/goods/save")
    public String goodsSave(@Valid GoodsRequest.GoodsSaveDTO goodsSaveDTO, Errors errors) {
        goodsService.goodsSave(goodsSaveDTO);
        return "redirect:/s/mypage-being-auctioned"; // 경매중인물품 리스트 화면으로 리다이렉트
    }

    // 경매시간 종료 상품 상태 변경
    @PostMapping("/goods-detail/goodsStatus/update")
    public ResponseEntity<?> updateStatus(@RequestBody GoodsRequest.GoodsStatusDTO goodsStatusDTO){

        //goodsService에 status 변경기능 위임
        goodsService.updateGoodsStatus(goodsStatusDTO);

        CommonResp resp = new CommonResp(true, "데이터 변경 성공", null);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/goods-list")
    public String goodsList(@RequestParam("select") String select,@RequestParam(defaultValue = "") String keyword, Model model) {
        model.addAttribute("category", categoryService.findAllCategory(null));
        model.addAttribute("goods", goodsService.searchGoodsList(GoodsRequest.SeacherGoodsDTO.builder().select(select).keyword(keyword).page(1).line(15).build()));
        model.addAttribute("keyword", new GoodsResponse.KeyWordDTO(keyword));
        return "goods-list";
    }

    @GetMapping("/goods-list/{id}")
    public String goodsList(@PathVariable("id") Integer categoryId, Model model) {
        model.addAttribute("category", categoryService.findAllCategory(categoryId));
        model.addAttribute("goods",goodsService.getGoodsList(categoryId,1,15));
        return "goods-list";
    }

    @ResponseBody
    @GetMapping("/api/v1/search-goods")
    public ResponseEntity<?> searchGoods(GoodsRequest.SeacherGoodsDTO goodsDTO) {
        List<GoodsResponse.GoodsDTO> dto = goodsService.searchGoodsList(goodsDTO);
        CommonResp<List<GoodsResponse.GoodsDTO>> resp = CommonResp.success(dto);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/api/v1/search-goods/{id}")
    public ResponseEntity<?> categoryGoods(@PathVariable("id") Integer id, Integer page, Integer line) {
        List<GoodsResponse.GoodsDTO> dto = goodsService.getGoodsList(id,page,line);
        CommonResp<List<GoodsResponse.GoodsDTO>> resp = CommonResp.success(dto);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    // 경매 중인 물품(판매) 화면 열기
    @GetMapping("/s/mypage-being-auctioned")
    public String beingAuctioned(@AuthenticationPrincipal User user, Model model, @RequestParam(defaultValue = "") String page) {
        List<GoodsResponse.UserGoodsDTO> userGoodsDTOS = goodsService.mySellGoods(user.getUsername(), page);
        int rowCount = goodsService.mySellGoodsAllCount(user.getId());
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount, 5));
        model.addAttribute("models", userGoodsDTOS);
        return "being-auctioned";
    }
}