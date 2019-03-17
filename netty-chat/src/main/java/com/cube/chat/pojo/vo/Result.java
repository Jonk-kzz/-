package com.cube.chat.pojo.vo;

/**
 * 将返回给客户端的数据封装到实体类中
 */
public interface Result {
    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作代码
    int code();
    //提示信息
    String message();



}
