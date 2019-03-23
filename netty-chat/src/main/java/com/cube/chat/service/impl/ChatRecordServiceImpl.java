package com.cube.chat.service.impl;

import com.cube.chat.dao.ChatRecordMapper;
import com.cube.chat.pojo.ChatRecord;
import com.cube.chat.pojo.ChatRecordExample;
import com.cube.chat.pojo.vo.CommonCode;
import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.ChatRecordService;
import com.cube.chat.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatRecordServiceImpl implements ChatRecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Autowired
    IdWorker idWorker;

    @Override
    public String insert(ChatRecord chatRecord) {
        chatRecord.setId(idWorker.nextId());
        chatRecord.setHasRead(0);
        chatRecord.setCreatetime(new Date());
        chatRecord.setHasDelete(0);
        chatRecordMapper.insert(chatRecord);
        return chatRecord.getId();
    }

    @Override
    public void updateStatusHasRead(String id) {
        ChatRecord chatRecord = chatRecordMapper.selectByPrimaryKey(id);
        chatRecord.setHasRead(1);
        chatRecordMapper.updateByPrimaryKeySelective(chatRecord);
    }

    /**
     * 根据朋友id 跟用户id查询未读的消息
     * @param userId
     * @param friendId
     * @return
     */
    @Override
    public ResponseResult findUnreadByUserIdAndFriendId(String userId, String friendId) {
        ChatRecordExample example=new ChatRecordExample();
        ChatRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andFriendidEqualTo(friendId);
        criteria.andHasReadEqualTo(0);
        criteria.andHasDeleteEqualTo(0);
        List<ChatRecord> chatRecords = chatRecordMapper.selectByExample(example);

        for (ChatRecord chatRecord : chatRecords) {
            chatRecord.setHasRead(1);
            chatRecordMapper.updateByPrimaryKeySelective(chatRecord);
        }

        return new ResponseResult(CommonCode.SUCCESS,chatRecords);
    }

    /**
     * 读取未读消息
     * @param userId
     * @return
     */
    @Override
    public ResponseResult findUnreadByUserId(String userId) {
        ChatRecordExample example=new ChatRecordExample();
        ChatRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andHasReadEqualTo(0);
        List<ChatRecord> chatRecords = chatRecordMapper.selectByExample(example);
        return new ResponseResult(CommonCode.SUCCESS,chatRecords);
    }
}
