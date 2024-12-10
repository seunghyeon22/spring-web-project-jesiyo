package com.metacoding.web_project.recode;

import com.metacoding.web_project._core.CommonResp;
import com.metacoding.web_project.goods.GoodsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
