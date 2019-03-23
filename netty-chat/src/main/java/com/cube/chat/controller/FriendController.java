package com.cube.chat.controller;

import com.cube.chat.pojo.FriendReq;
import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    /**
     * 添加好友
     * @param friendReq
     * @return
     */
    @PostMapping("/sendRequest")
    public ResponseResult sendRequest(@RequestBody FriendReq friendReq){
        return friendService.sendRequest(friendReq);
    }

    /**
     * 加载好友请求信息
     * @param userId
     * @return
     */
    @GetMapping("/findFriendReqByUserId/{userId}")
    public ResponseResult findFriendReqByUserId(@PathVariable("userId")String userId){
        return friendService.findFriendReqByUserId(userId);
    }

    @PostMapping("/acceptFriendReq/{reqId}")
    public ResponseResult acceptFriendReq(@PathVariable("reqId")String reqId){
        return friendService.acceptFriendReq(reqId);
    }

    @PostMapping("/ignoreFiendReq/{reqId}")
    public ResponseResult ignoreFriendReq(@PathVariable("reqId") String reqId){
        return friendService.ignoreFriendReq(reqId);
    }

    @PostMapping("/findFriendByUserId/{userId}")
    public ResponseResult findFriendByUserId(@PathVariable("userId")String userId){
        return friendService.findFriendByUserId(userId);
    }
}
