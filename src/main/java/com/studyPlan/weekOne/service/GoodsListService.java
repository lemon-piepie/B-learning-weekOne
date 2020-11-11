package com.studyPlan.weekOne.service;

import com.studyPlan.weekOne.entity.GoodsItem;
import com.studyPlan.weekOne.repository.GoodsItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoodsListService {
    private final GoodsItemRepository goodsItemRepository;

    public GoodsListService(GoodsItemRepository goodsItemRepository){
        this.goodsItemRepository=goodsItemRepository;
    }

    public void addNewGoodsItem(GoodsItem goodsItem) {
        goodsItemRepository.save(goodsItem);
    }
}
