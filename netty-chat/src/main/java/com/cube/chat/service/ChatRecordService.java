package com.cube.chat.service;

import com.cube.chat.pojo.ChatRecord;
import com.cube.chat.pojo.vo.ResponseResult;

public interface ChatRecordService {
    String insert(ChatRecord chatRecord);

    void updateStatusHasRead(String id);

    ResponseResult findUnreadByUserIdAndFriendId(String userId, String friendId);

    ResponseResult findUnreadByUserId(String userId);

}
