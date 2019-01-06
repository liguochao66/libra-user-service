package com.struggle.libra.user.service.entities.cache;

import java.text.MessageFormat;

public final class LibraUserRedisKeys {

    /**
     * 默认缓存命名空间
     */
    public final static String LIBRA_USER_DEFAULT_NAME_SPACE = "libra:user";

    /**
     * 获取用户详情信息缓存键值对
     *
     * @param id 用户编号
     * @return 用户详情信息缓存键值对
     */
    public static String getUserDetailsRedisKey(String id) {
        return MessageFormat.format("{0}:user:details:{1}", LIBRA_USER_DEFAULT_NAME_SPACE, id);
    }
}