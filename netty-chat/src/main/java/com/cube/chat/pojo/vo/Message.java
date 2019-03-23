package com.cube.chat.pojo.vo;

import com.cube.chat.pojo.ChatRecord;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Message implements Serializable {

    //消息类型
    private Integer type;
    //消息体
    private ChatRecord chatRecord;
    //扩展字段
    private String ext;

}
