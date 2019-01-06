package com.struggle.libra.user.service.register;

import com.struggle.libra.user.service.entities.model.partner.PartnerInfo;
import com.struggle.libra.user.service.entities.model.user.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {

    /**
     * 匿名注册
     *
     * @param request  应用请求上下文
     * @param response 应用响应上下文
     * @return 用户信息
     */
    UserInfo registerByDeviceInfo(HttpServletRequest request, HttpServletResponse response);

    /**
     * 显示注册
     *
     * @param info     三方信息
     * @param request  应用请求上下文
     * @param response 应用响应上下文
     * @return 用户信息
     */
    UserInfo registerByPartnerInfo(PartnerInfo info, HttpServletRequest request, HttpServletResponse response);
}