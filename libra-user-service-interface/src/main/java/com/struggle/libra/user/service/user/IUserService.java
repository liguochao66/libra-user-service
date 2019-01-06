package com.struggle.libra.user.service.user;

import com.struggle.libra.user.service.entities.model.user.UserInfo;

/**
 * @author james.li
 */
public interface IUserService {

    /**
     * 获取用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserInfo getUserInfo(String id);
}