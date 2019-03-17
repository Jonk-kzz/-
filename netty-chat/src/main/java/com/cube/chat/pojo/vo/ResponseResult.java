package com.cube.chat.pojo.vo;

import lombok.Data;

@Data
public class ResponseResult {
    //操作是否成功
    boolean success ;

    //操作代码
    int code;

    //提示信息
    String message;

    private Object data;


    public ResponseResult(){}

    public ResponseResult(CommonCode commonCode) {
        this.success = commonCode.success;
        this.code = commonCode.code;
        this.message = commonCode.message;
    }

    public ResponseResult(CommonCode commonCode, Object data) {
        this.success = commonCode.success;
        this.code = commonCode.code;
        this.message = commonCode.message;
        this.data = data;
    }
}
