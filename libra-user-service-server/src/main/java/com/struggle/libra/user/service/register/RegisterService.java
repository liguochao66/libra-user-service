package com.struggle.libra.user.service.register;

import com.struggle.libra.user.service.context.IContextService;
import com.struggle.libra.user.service.entities.UserPartner;
import com.struggle.libra.user.service.entities.UserSource;
import com.struggle.libra.user.service.entities.model.device.DeviceInfo;
import com.struggle.libra.user.service.entities.model.partner.PartnerInfo;
import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.mybatis.UserDao;
import com.struggle.libra.user.service.sources.ISourcesService;
import com.struggle.libra.user.service.sources.branches.redis.IRedisService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.Objects;

/**
 * @author james.li
 */
@Path("reg")
@Service(value = "registerService")
public class RegisterService implements IRegisterService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "redisService")
    private IRedisService redisService;

    @Resource(name = "sourcesService")
    private ISourcesService sourcesService;

    @Resource(name = "contextService")
    private IContextService contextService;

    @POST
    @Override
    @Path("usd/1")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public UserInfo registerByDeviceInfo(@Context HttpServletRequest request, @Context HttpServletResponse response) {

        /*
         * data init
         */
        boolean action = true;
        DeviceInfo deviceInfo;
        UserInfo userInfo = null;

        /*
         * execute
         */
        try {

            /*
             * validation
             */
            action = Objects.nonNull(deviceInfo = contextService.getDeviceInfo(request, response));

            /*
             * execute
             */
            if (action) {

                /*
                 * get userId
                 */
                final String id = DigestUtils.md5Hex(deviceInfo.getDi());

                /*
                 * get userInfo from data service
                 */
                userInfo = sourcesService.getUserInfo(id);
                if (Objects.isNull(userInfo)) {

                    /*
                     * get userInfo from runtime
                     */
                    UserInfo dataResult = new UserInfo();
                    dataResult.setId(id);
                    dataResult.setNickName("1");
                    dataResult.setPortrait("2");
                    dataResult.setUpi(UserPartner.UPW.name());
                    dataResult.setUsi(UserSource.USD.name());

                    /*
                     * insert userInfo into data service
                     */
                    if (action = (userDao.insert(dataResult) == 1)) {

                        /*
                         * copy userInfo
                         */
                        userInfo = dataResult;

                        /*
                         * insert or update userInfo into redis service
                         */
                        action = redisService.setUserInfo(dataResult);
                    }
                }
            }
        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_REGISTER_SERVICE.error("register by deviceInfo, error:{}", ex);
        } finally {
            LogBackToolkit.LOGGER_REGISTER_SERVICE.info("register by deviceInfo, action:{}", action);
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
    public UserInfo registerByPartnerInfo(@RequestBody PartnerInfo info, @Context HttpServletRequest request, @Context HttpServletResponse response) {

        /*
         * data init
         */
        boolean action = true;
        DeviceInfo deviceInfo;
        UserInfo userInfo = null;

        /*
         * execute
         */
        try {

            /*
             * validation
             */
            action = Objects.nonNull(deviceInfo = contextService.getDeviceInfo(request, response));

            /*
             * execute
             */
            if (action) {

                /*
                 * get userId
                 */
                final String id = DigestUtils.md5Hex(info.getPi());

                /*
                 * get userInfo from data service
                 */
                userInfo = sourcesService.getUserInfo(deviceInfo.getId());
                if (Objects.isNull(userInfo)) {

                    /*
                     * get userInfo from runtime
                     */
                    UserInfo dataResult = new UserInfo();
                    dataResult.setId(id);
                    dataResult.setNickName(info.getNickName());
                    dataResult.setPortrait(info.getPortrait());
                    dataResult.setUsi(UserSource.USP.name());
                    dataResult.setUpi(UserPartner.UPW.name());

                    /*
                     * insert userInfo into data service
                     */
                    if (action = (userDao.insert(dataResult) == 1)) {

                        /*
                         * copy userInfo
                         */
                        userInfo = dataResult;

                        /*
                         * insert or update userInfo into redis service
                         */
                        action = redisService.setUserInfo(dataResult);
                    }
                }
            }
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