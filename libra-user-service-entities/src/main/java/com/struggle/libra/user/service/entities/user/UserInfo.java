package com.struggle.libra.user.service.entities.user;

import com.yueke.gemini.common.data.mongo.entities.MongoData;

public class UserInfo extends MongoData {

    /**
     * 昵称
     */
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 头像
     */
    private String portrait;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 来源
     */
    private String sourceId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 可用状态
     */
    private short enableStatus = 1;

    public short getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(short enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 删除状态
     */
    private short deleteStatus = 0;

    public short getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}