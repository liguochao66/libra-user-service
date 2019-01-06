package com.struggle.libra.user.service.entities;

public enum UserPartner {

    /**
     * 设备注册
     */
    UPD("2001", "设备注册"),

    /**
     * 三方注册
     */
    UPW("2002", "微信注册");

    /**
     * 构造函数
     *
     * @param id   编号
     * @param desc 描述
     */
    UserPartner(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    /**
     * 编号
     */
    private String id;

    public String getId() {
        return id;
    }

    /**
     * 描述
     */
    private String desc;

    public String getDesc() {
        return desc;
    }
}