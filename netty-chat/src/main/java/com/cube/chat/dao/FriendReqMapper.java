package com.cube.chat.dao;

import com.cube.chat.pojo.FriendReq;
import com.cube.chat.pojo.FriendReqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendReqMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int countByExample(FriendReqExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int deleteByExample(FriendReqExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int insert(FriendReq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int insertSelective(FriendReq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    List<FriendReq> selectByExample(FriendReqExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    FriendReq selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FriendReq record, @Param("example") FriendReqExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FriendReq record, @Param("example") FriendReqExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FriendReq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_friend_req
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FriendReq record);


}