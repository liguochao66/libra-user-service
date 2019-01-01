package com.struggle.libra.user.service.register;

import com.struggle.libra.user.service.entities.device.DeviceInfo;
import com.struggle.libra.user.service.entities.partner.PartnerInfo;
import com.struggle.libra.user.service.entities.user.UserInfo;
import com.struggle.libra.user.service.mybatis.UserDao;
import com.struggle.libra.user.service.sources.ISourcesService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author james.li
 */
@Path("reg")
@Service(value = "registerService")
public class RegisterService implements IRegisterService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "sourcesService")
    private ISourcesService sourcesService;

    @POST
    @Override
    @Path("usd/1")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public UserInfo registerByDeviceInfo(@RequestBody DeviceInfo info) {

        /*
         * data init
         */
        boolean action = true;
        UserInfo userInfo = null;

        /*
         * execute
         */
        try {
        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_REGISTER_SERVICE.error("register by deviceInfo, deviceInfo:{}, error:{}", info, ex);
        } finally {
            LogBackToolkit.LOGGER_REGISTER_SERVICE.info("register by deviceInfo, deviceInfo:{}, action:{}", info, action);
        }

        /*
         * return
         */
        return userInfo;
    }

    @POST
    @Override
    @Path("usp/1")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public UserInfo registerByPartnerInfo(@RequestBody PartnerInfo info) {

        /*
         * data init
         */
        boolean action = true;
        UserInfo userInfo = null;

        /*
         * execute
         */
        try {
        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_REGISTER_SERVICE.error("register by partnerInfo, partnerInfo:{}, error:{}", info, ex);
        } finally {
            LogBackToolkit.LOGGER_REGISTER_SERVICE.info("register by partnerInfo, partnerInfo:{}, action:{}", info, action);
        }

        /*
         * return
         */
        return userInfo;
    }
}