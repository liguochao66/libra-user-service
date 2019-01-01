package com.struggle.libra.user.service.entities;

public enum UserSource {

    /**
     * 设备注册
     */
    USD("1001", "设备注册"),

    /**
     * 三方注册
     */
    USP("1002", "三方注册");

    /**
     * 构造函数
     *
     * @param id   编号
     * @param desc 描述
     */
    UserSource(String id, String desc) {
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