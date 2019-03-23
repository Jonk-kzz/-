package com.cube.chat.pojo.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class FriendResponse {
    private String id;
    private String username;
    private String picSmall;
    private String picNormal;
    private String nickname;
    private String qrcode;
    private String clientId;
    private String sign;
    private Date createtime;
    private String phone;


}
