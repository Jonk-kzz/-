package com.cube.chat.pojo;

import java.util.Date;

public class ChatRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.userid
     *
     * @mbggenerated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.friendid
     *
     * @mbggenerated
     */
    private String friendid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.has_read
     *
     * @mbggenerated
     */
    private Integer hasRead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.has_delete
     *
     * @mbggenerated
     */
    private Integer hasDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_chat_record.message
     *
     * @mbggenerated
     */
    private String message;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.id
     *
     * @return the value of tb_chat_record.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.id
     *
     * @param id the value for tb_chat_record.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.userid
     *
     * @return the value of tb_chat_record.userid
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.userid
     *
     * @param userid the value for tb_chat_record.userid
     *
     * @mbggenerated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.friendid
     *
     * @return the value of tb_chat_record.friendid
     *
     * @mbggenerated
     */
    public String getFriendid() {
        return friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.friendid
     *
     * @param friendid the value for tb_chat_record.friendid
     *
     * @mbggenerated
     */
    public void setFriendid(String friendid) {
        this.friendid = friendid == null ? null : friendid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.has_read
     *
     * @return the value of tb_chat_record.has_read
     *
     * @mbggenerated
     */
    public Integer getHasRead() {
        return hasRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.has_read
     *
     * @param hasRead the value for tb_chat_record.has_read
     *
     * @mbggenerated
     */
    public void setHasRead(Integer hasRead) {
        this.hasRead = hasRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.createtime
     *
     * @return the value of tb_chat_record.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.createtime
     *
     * @param createtime the value for tb_chat_record.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.has_delete
     *
     * @return the value of tb_chat_record.has_delete
     *
     * @mbggenerated
     */
    public Integer getHasDelete() {
        return hasDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.has_delete
     *
     * @param hasDelete the value for tb_chat_record.has_delete
     *
     * @mbggenerated
     */
    public void setHasDelete(Integer hasDelete) {
        this.hasDelete = hasDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_chat_record.message
     *
     * @return the value of tb_chat_record.message
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_chat_record.message
     *
     * @param message the value for tb_chat_record.message
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}