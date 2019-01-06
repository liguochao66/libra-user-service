package com.struggle.libra.user.service.sources;

import com.struggle.libra.user.service.entities.model.user.UserInfo;

public interface ISourcesService {

    /**
     * 根据用户编号获取用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserInfo getUserInfo(String id);
}
