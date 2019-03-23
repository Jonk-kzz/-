package com.cube.chat.service.impl;

import com.cube.chat.dao.FriendMapper;
import com.cube.chat.dao.FriendReqMapper;
import com.cube.chat.dao.UserMapper;
import com.cube.chat.pojo.*;
import com.cube.chat.pojo.vo.CommonCode;
import com.cube.chat.pojo.vo.FriendResponse;
import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.FriendService;
import com.cube.chat.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    FriendMapper friendMapper;

    @Autowired
    FriendReqMapper friendReqMapper;

    @Autowired
    UserMapper userMapper;



    /**
     * 添加好友
     * @param friendReq
     * @return
     */
    @Override
    public ResponseResult sendRequest(FriendReq friendReq) {

        FriendReqExample example=new FriendReqExample();
        FriendReqExample.Criteria criteria = example.createCriteria();
        criteria.andFromUseridEqualTo(friendReq.getFromUserid());
        criteria.andToUseridEqualTo(friendReq.getToUserid());

        List<FriendReq> reqList = friendReqMapper.selectByExample(example);
        if(reqList!=null && reqList.size()>0){
            FriendReq existReq = reqList.get(0);
            Integer status = existReq.getStatus();
            if(status==0){
                return new ResponseResult(CommonCode.FRIENDREQ_NOT_PASS);
            }else{
                return new ResponseResult(CommonCode.FRIENDREQ_EXIST);
            }
        }else{
            friendReq.setId(idWorker.nextId());
            friendReq.setCreatetime(new Date());
            friendReq.setStatus(0);
            friendReqMapper.insert(friendReq);
        }

        return new ResponseResult(CommonCode.SUCCESS,friendReq);
    }

    @Override
    public ResponseResult findFriendReqByUserId(String userId) {
        List<FriendResponse> list=new ArrayList<>();

        FriendReqExample example=new FriendReqExample();
        FriendReqExample.Criteria criteria = example.createCriteria();
        criteria.andToUseridEqualTo(userId);
        criteria.andStatusEqualTo(0);

        List<FriendReq> reqList = friendReqMapper.selectByExample(example);
        for (FriendReq friendReq : reqList) {
            String fromUserId = friendReq.getFromUserid();
            User user = userMapper.selectByPrimaryKey(fromUserId);
            FriendResponse reUser=new FriendResponse();
            BeanUtils.copyProperties(user,reUser);
            reUser.setId(friendReq.getId());
            list.add(reUser);
        }
        return new ResponseResult(CommonCode.SUCCESS,list);
    }

    @Override
    public ResponseResult acceptFriendReq(String reqId) {
        FriendReq friendReq = friendReqMapper.selectByPrimaryKey(reqId);
        friendReq.setStatus(1);
        friendReqMapper.updateByPrimaryKeySelective(friendReq);

        //互相添加为好友
        //添加申请好友
        Friend friend1 = new Friend();
        friend1.setId(idWorker.nextId());
        //用户id
        friend1.setUserid(friendReq.getFromUserid());
        //好友id
        friend1.setFriendsId(friendReq.getToUserid());
        friend1.setCreatetime(new Date());

        Friend friend2=new Friend();
        friend2.setId(idWorker.nextId());
        friend2.setUserid(friendReq.getToUserid());
        friend2.setFriendsId(friendReq.getFromUserid());
        friend2.setCreatetime(new Date());

        friendMapper.insert(friend1);
        friendMapper.insert(friend2);

        //发送消息更新通讯录
        //获取发送好友请求发Channel

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult ignoreFriendReq(String reqId) {
        friendReqMapper.deleteByPrimaryKey(reqId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult  findFriendByUserId(String userId) {
        List<FriendResponse> list=new ArrayList<>();

        FriendExample example=new FriendExample();
        FriendExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);

        List<Friend> friendList = friendMapper.selectByExample(example);
        for (Friend friend : friendList) {
            String friendsId = friend.getFriendsId();
            User user = userMapper.selectByPrimaryKey(friendsId);
            //用户信息统一结构
            FriendResponse friendResponse=new FriendResponse();
            BeanUtils.copyProperties(user,friendResponse);
            list.add(friendResponse);
        }
        return new ResponseResult(CommonCode.SUCCESS,list);
    }
}
