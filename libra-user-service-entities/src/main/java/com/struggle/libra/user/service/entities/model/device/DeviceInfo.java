package com.struggle.libra.user.service.entities.model.device;

import com.alibaba.fastjson.JSON;
import com.yueke.gemini.common.data.mongo.entities.MongoData;

/**
 * @author james.li
 */
public class DeviceInfo extends MongoData {

    /**
     * 设备编号
     */
    private String di;

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    /**
     * 设备类型
     */
    private String dt;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * 网络类型
     */
    private String net;

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    /**
     * 系统类型
     */
    private String ost;

    public String getOst() {
        return ost;
    }

    public void setOst(String ost) {
        this.ost = ost;
    }

    /**
     * 系统版本
     */
    private String osv;

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
    }

    /**
     * 运营商
     */
    private String sim;

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    /**
     * 设备详情
     */
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}