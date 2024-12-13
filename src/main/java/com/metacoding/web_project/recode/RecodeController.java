package com.metacoding.web_project.recode;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project._core.util.PageUtil;
import com.metacoding.web_project.bid.BidResponse;
import com.metacoding.web_project.goods.GoodsRequest;
import com.metacoding.web_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RecodeController {

    private final RecodeService recodeService;

    @PostMapping("/goods-detail/saveRecode")
    public ResponseEntity<?> saveRecode(@RequestBody GoodsRequest.GoodsStatusDTO statusDTO) {
        recodeService.save(statusDTO.getId());

        CommonResp resp = new CommonResp(true, "데이터 옮겨담기 성공", null);
        return ResponseEntity.ok(resp);
    }

    // 입찰 실패 이력 페이지 이동
    @GetMapping("/s/myPage-participate-fail")
    public String participateFail(@AuthenticationPrincipal User user, Model model, @RequestParam(defaultValue = "") String divide, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String page) {
        List<RecodeResponse.ParticipateFailDTO> dtoList = recodeService.findFailRecodeByUserId(user.getId(), divide, search, page);
        Integer rowCount = recodeService.countFailedRecodeByUserId(user.getId(), divide, search);
        model.addAttribute("pagination", PageUtil.returnToPageDTO(page, rowCount));
        model.addAttribute("divide", divide);
        model.addAttribute("search", search);
        model.addAttribute("model", dtoList);

        return "participate-fail";
    }

}
