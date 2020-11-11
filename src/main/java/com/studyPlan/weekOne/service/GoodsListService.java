package com.studyPlan.weekOne.service;

import com.studyPlan.weekOne.entity.GoodsItem;
import com.studyPlan.weekOne.exception.GoodsItemNotFoundById;
import com.studyPlan.weekOne.repository.GoodsItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<GoodsItem> getAllGoodsItem () {
        return goodsItemRepository.findAll();
    }

    public void deleteGoodsItem (Integer id) {
        if (goodsItemRepository.findById(id).isPresent()) {
            goodsItemRepository.deleteById(id);
        } else {
            throw new GoodsItemNotFoundById();
        }
    }

    public void updateGoodsItem(GoodsItem goodsItem) {
        if (goodsItemRepository.findById(goodsItem.getId()).isPresent()) {
            GoodsItem target = goodsItemRepository.findById(goodsItem.getId()).get();
            target.setName(goodsItem.getName());
            target.setUrl(goodsItem.getUrl());
            target.setShop(goodsItem.getShop());
            target.setUnitPrice(goodsItem.getUnitPrice());
            goodsItemRepository.save(target);
        } else {
            throw new GoodsItemNotFoundById();
        }
    }
}
