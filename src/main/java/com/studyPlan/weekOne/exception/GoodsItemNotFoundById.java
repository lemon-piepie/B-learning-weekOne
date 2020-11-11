package com.studyPlan.weekOne.exception;

public class GoodsItemNotFoundById extends RuntimeException{
    public GoodsItemNotFoundById() {
        super("该商品不存在");
    }
}
