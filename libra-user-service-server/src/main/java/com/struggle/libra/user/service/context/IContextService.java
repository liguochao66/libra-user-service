package com.struggle.libra.user.service.context;

import com.struggle.libra.user.service.entities.model.device.DeviceInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IContextService {

    /**
     * 获取设备详情信息
     *
     * @param request  应用请求上下文
     * @param response 应用响应上下文
     * @return 设备详情信息
     */
    DeviceInfo getDeviceInfo(HttpServletRequest request, HttpServletResponse response);
}