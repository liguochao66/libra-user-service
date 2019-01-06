package com.struggle.libra.user.service.context.impl;

import com.alibaba.fastjson.JSON;
import com.struggle.libra.user.service.context.IContextService;
import com.struggle.libra.user.service.entities.config.RequestConfig;
import com.struggle.libra.user.service.entities.model.device.DeviceInfo;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service(value = "contextService")
public class ContextService implements IContextService {

    @Override
    public DeviceInfo getDeviceInfo(HttpServletRequest request, HttpServletResponse response) {

        /*
         * data init
         */
        String val = null;
        boolean action = true;
        DeviceInfo deviceInfo = null;

        /*
         * execute
         */
        try {
            action = Objects.nonNull(request);
            action = action && StringUtils.isNotBlank(val = request.getHeader(RequestConfig.CONFIG_REQUEST_HEADER_DEVICE_KEY));
            action = action && Objects.nonNull(deviceInfo = JSON.parseObject(val, DeviceInfo.class));
        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_CONTEXT_SERVICE.error("get deviceInfo from context service, error:{}", ex);
        } finally {
            LogBackToolkit.LOGGER_CONTEXT_SERVICE.info("get deviceInfo from context service, action:{}", action);
        }

        /*
         * return
         */
        return deviceInfo;
    }
}