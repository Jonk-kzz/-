package com.cube.chat.pojo.vo;

import lombok.ToString;

@ToString
public enum CommonCode implements Result{
    SUCCESS(true,200,"操作成功！"),
    FAIL(false,300,"操作失败！"),
    INVALID_PARAM(false,10003,"非法参数!"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    USER_EXIST(false,300,"用户名以存在"),
    REGISTER_SUCCESS(true,200,"注册成功"),
    INIT_FILE_FALL(false,500,"初始化文件系统出错"),
    LOGIN_FALL(false,500,"登陆失败,将检查用户名或密码是否正确"),
    FRIENDREQ_EXIST(false,300,"已经请求过了"),
    FRIENDREQ_CURRENT(false,300,"无法添加自己为好友"),
    FRIENDREQ_NOT_PASS(false,300,"已经请求过了,等待好友通过");


    // 是否操作成功
    boolean success;
    // 返回消息
    String message;

    Integer code;

    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
