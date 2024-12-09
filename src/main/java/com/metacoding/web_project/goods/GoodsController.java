package com.metacoding.web_project.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class GoodsController {
    private final GoodsService goodsService;
}
