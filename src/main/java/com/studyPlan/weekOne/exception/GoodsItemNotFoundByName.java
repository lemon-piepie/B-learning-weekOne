package com.studyPlan.weekOne.exception;

public class GoodsItemNotFoundByName extends RuntimeException{
    public GoodsItemNotFoundByName() {
        super("没有找到相应的商品，请重新输入关键词");
    }
}