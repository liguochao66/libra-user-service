package com.struggle.libra.user.service.register;

import com.struggle.libra.user.service.entities.device.DeviceInfo;
import com.struggle.libra.user.service.entities.partner.PartnerInfo;
import com.struggle.libra.user.service.entities.user.UserInfo;

public interface IRegisterService {

    /**
     * 匿名注册
     *
     * @param info 设备注册
     * @return 用户信息
     */
    UserInfo registerByDeviceInfo(DeviceInfo info);

    /**
     * 显示注册
     *
     * @param info 三方信息
     * @return 用户信息
     */
    UserInfo registerByPartnerInfo(PartnerInfo info);
}