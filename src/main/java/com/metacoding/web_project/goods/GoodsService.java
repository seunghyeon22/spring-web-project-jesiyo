package com.metacoding.web_project.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
}
