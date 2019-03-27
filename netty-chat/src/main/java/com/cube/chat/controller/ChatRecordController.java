package com.cube.chat.controller;

import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chatRecord")
@RestController
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 根据 用户id跟朋友id查询未读的消息
     * @param userId
     * @param friendId
     * @return
     */
    @RequestMapping("/findUnreadByUserIdAndFriendId")
    public ResponseResult findUnreadByUserIdAndFriendId(String userId,String friendId){
       return chatRecordService.findUnreadByUserIdAndFriendId(userId,friendId);
    }

    @RequestMapping("/findUnreadByUserId/{userId}")
    public ResponseResult findUnreadByUserId(@PathVariable("userId") String userId){
        return chatRecordService.findUnreadByUserId(userId);
    }
}
