package com.cube.chat.service;

import com.cube.chat.pojo.FriendReq;
import com.cube.chat.pojo.vo.ResponseResult;

public interface FriendService {
    ResponseResult sendRequest(FriendReq friendReq);

    ResponseResult findFriendReqByUserId(String userId);

    ResponseResult acceptFriendReq(String reqId);

    ResponseResult ignoreFriendReq(String reqId);

    ResponseResult findFriendByUserId(String userId);
}
