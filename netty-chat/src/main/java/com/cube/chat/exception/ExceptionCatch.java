package com.cube.chat.exception;

import com.cube.chat.pojo.vo.CommonCode;
import com.cube.chat.pojo.vo.ResponseResult;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //使用EXCEPTIONS存放异常类型和错误代码的映射,ImmutableMap的特点一旦创建不可改变,并且线程安全
    private static ImmutableMap<Class<? extends Throwable>, ResponseResult> EXCEPTIONS;
    //使用builder来构建一个异常类型和错误代码的异常
    protected  static ImmutableMap.Builder<Class<? extends Throwable>,ResponseResult> builder=ImmutableMap.builder();

    //捕获 CustomException异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error("catch exception : {}\r\nexception : ",e.getMessage());

        ResponseResult result = e.getResult();

        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e){
        //记录日志
        LOGGER.error("catch exception:{}",e.getMessage());
        if(EXCEPTIONS == null){
            EXCEPTIONS=builder.build();
        }
        ResponseResult result = EXCEPTIONS.get(e.getClass());
        ResponseResult responseResult;
        if(result != null){
           return  result;
        }else{
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

}
