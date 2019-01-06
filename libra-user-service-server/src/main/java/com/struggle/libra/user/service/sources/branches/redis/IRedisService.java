package com.struggle.libra.user.service.sources.branches.redis;

import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.sources.ISourcesService;

public interface IRedisService extends ISourcesService {

    /**
     * 保存用户信息到缓存系统中
     *
     * @param userInfo 用户信息
     * @return 保存操作状态结果
     */
    boolean setUserInfo(UserInfo userInfo);
}
