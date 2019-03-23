package com.cube.chat.pojo;

import java.util.Date;

public class FriendReq {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.from_userid
     *
     * @mbggenerated
     */
    private String fromUserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.to_userid
     *
     * @mbggenerated
     */
    private String toUserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.message
     *
     * @mbggenerated
     */
    private String message;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_friend_req.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.id
     *
     * @return the value of tb_friend_req.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.id
     *
     * @param id the value for tb_friend_req.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.from_userid
     *
     * @return the value of tb_friend_req.from_userid
     *
     * @mbggenerated
     */
    public String getFromUserid() {
        return fromUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.from_userid
     *
     * @param fromUserid the value for tb_friend_req.from_userid
     *
     * @mbggenerated
     */
    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid == null ? null : fromUserid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.to_userid
     *
     * @return the value of tb_friend_req.to_userid
     *
     * @mbggenerated
     */
    public String getToUserid() {
        return toUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.to_userid
     *
     * @param toUserid the value for tb_friend_req.to_userid
     *
     * @mbggenerated
     */
    public void setToUserid(String toUserid) {
        this.toUserid = toUserid == null ? null : toUserid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.createtime
     *
     * @return the value of tb_friend_req.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.createtime
     *
     * @param createtime the value for tb_friend_req.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.message
     *
     * @return the value of tb_friend_req.message
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.message
     *
     * @param message the value for tb_friend_req.message
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_friend_req.status
     *
     * @return the value of tb_friend_req.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_friend_req.status
     *
     * @param status the value for tb_friend_req.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}