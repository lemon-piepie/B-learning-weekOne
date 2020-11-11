package com.studyPlan.weekOne.controller;

import com.studyPlan.weekOne.entity.GoodsItem;
import com.studyPlan.weekOne.service.GoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/goodsItem")
public class GoodsListController {

    @Autowired
    GoodsListService goodsListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewGoodsItem (@RequestBody @Valid GoodsItem goodsItem) {
        goodsListService.addNewGoodsItem(goodsItem);
    }

    @GetMapping
    public List<GoodsItem> getAllGoodsItem () {
        return goodsListService.getAllGoodsItem();
    }

}
