package com.cube.chat.exception;


import com.cube.chat.pojo.vo.ResponseResult;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private ResponseResult result;

    public CustomException(ResponseResult result){
        //异常信息为错误代码+异常信息
        //super("错误代码:"+resultCode.code()+"错误信息:"+resultCode.message());
        this.result = result;
    }

}
