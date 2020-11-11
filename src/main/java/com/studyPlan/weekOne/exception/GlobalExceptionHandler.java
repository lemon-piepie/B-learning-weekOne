package com.studyPlan.weekOne.exception;

import com.studyPlan.weekOne.entity.GoodsItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult userAndEducationValid(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ErrorResult(new Date(),400,"BAD REQUEST",message);
    }

    @ExceptionHandler(GoodsItemNotFoundById.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult goodsNotExist(GoodsItemNotFoundById ex){
        return new ErrorResult(new Date(),404,"NOT FOUND",ex.getMessage());
    }

    @ExceptionHandler(GoodsItemNotFoundByName.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult goodsNotFound(GoodsItemNotFoundByName ex){
        return new ErrorResult(new Date(),404,"NOT FOUND",ex.getMessage());
    }
}