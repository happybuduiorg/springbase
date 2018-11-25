package com.happybudui.springbase.exception;

import com.happybudui.springbase.wrapper.ResponseResult;
import com.happybudui.springbase.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@RestControllerAdvice
public class SpringBaseExceptionManager {

    private Logger logger= LoggerFactory.getLogger(SpringBaseExceptionManager.class);

    @ExceptionHandler(Exception.class)
    public ResponseResult<Integer> handlerException(Exception e) {
        //如果是自定义的异常，返回对应的错误信息
        if (e instanceof SpringBaseException) {
            logger.info(e.getMessage());
            return ResultGenerator.error(((SpringBaseException) e).getErrorcode(),e.getMessage());
        } else {
            //如果不是已知异常，返回系统异常
            logger.info(e.getMessage());
            return ResultGenerator.error(200, "System Error!");
        }
    }
}
