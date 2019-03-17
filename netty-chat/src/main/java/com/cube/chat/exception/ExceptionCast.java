package com.cube.chat.exception;

import com.cube.chat.pojo.vo.ResponseResult;

public class ExceptionCast {

    //使用此静态方法抛出自定义异常
    public static void cast(ResponseResult result){
        throw new CustomException(result);
    }
}
