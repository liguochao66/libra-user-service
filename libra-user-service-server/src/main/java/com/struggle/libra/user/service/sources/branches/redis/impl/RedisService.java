package com.struggle.libra.user.service.sources.branches.redis.impl;

import com.alibaba.fastjson.JSON;
import com.struggle.libra.user.service.entities.cache.LibraUserRedisKeys;
import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.sources.branches.redis.IRedisService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import com.yueke.gemini.common.data.codis.CodisFactory;
import com.yueke.gemini.common.data.codis.executor.CommandExecutor;
import com.yueke.gemini.common.data.validation.utils.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service(value = "redisService")
public class RedisService implements IRedisService {

    @Resource(name = "codisFactory")
    private CodisFactory codisFactory;

    @Override
    public boolean setUserInfo(UserInfo userInfo) {

        /*
         * data init
         */
        Boolean result;
        boolean action = true;

        /*
         * execute
         */
        try {

            /*
             * validation
             */
            action = Objects.nonNull(userInfo);
            action = action && !ValidationUtils.validate(userInfo).isHasErrors();

            /*
             * execute
             */
            action = action && ((result = codisFactory.execute(new CommandExecutor<Boolean>() {
                @Override
                public Boolean execute(Jedis connection) {

                    /*
                     * data init
                     */
                    final String key = LibraUserRedisKeys.getUserDetailsRedisKey(userInfo.getId());

                    /*
                     * execute
                     */
                    connection.setex(key, (int) TimeUnit.HOURS.toSeconds(1), JSON.toJSONString(userInfo));

                    /*
                     * return
                     */
                    return Boolean.TRUE;
                }
            }, LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE)) != null && result);

        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE.error("set userInfo into redis service, userInfo:{}, error:{}", userInfo, ex);
        } finally {
            LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE.info("set userInfo into redis service, userInfo:{}, action:{}", userInfo, action);
        }

        /*
         * return
         */
        return action;
    }

    @Override
    public UserInfo getUserInfo(String id) {

        /*
         * data init
         */
        String val;
        boolean action = true;
        UserInfo userInfo = null;

        /*
         * execute
         */
        try {

            /*
             * validation
             */
            action = StringUtils.isNotBlank(id);

            /*
             * execute
             */
            action = action && StringUtils.isNotBlank(val = codisFactory.execute(new CommandExecutor<String>() {
                @Override
                public String execute(Jedis connection) {
                    return connection.get(LibraUserRedisKeys.getUserDetailsRedisKey(id));
                }
            }, LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE)) && Objects.nonNull(userInfo = JSON.parseObject(val, UserInfo.class));

        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE.error("get userInfo from redis service, id:{}, error:{}", id, ex);
        } finally {
            LogBackToolkit.LOGGER_SOURCE_REDIS_SERVICE.info("get userInfo from redis service, id:{}, action:{}", id, action);
        }

        /*
         * return
         */
        return userInfo;
    }
}
