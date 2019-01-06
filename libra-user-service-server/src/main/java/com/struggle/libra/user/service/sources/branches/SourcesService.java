package com.struggle.libra.user.service.sources.branches;

import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.sources.ISourcesService;
import com.struggle.libra.user.service.sources.branches.data.IDataService;
import com.struggle.libra.user.service.sources.branches.redis.IRedisService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service(value = "sourcesService")
public class SourcesService implements ISourcesService {

    @Resource(name = "dataService")
    private IDataService dataService;

    @Resource(name = "redisService")
    private IRedisService redisService;

    @Override
    public UserInfo getUserInfo(String id) {

        /*
         * data init
         */
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
            if (action) {

                /*
                 * get userInfo from redis service
                 */
                action = Objects.nonNull(userInfo = redisService.getUserInfo(id));

                /*
                 * get userInfo from data service
                 */
                if (!action && (action = Objects.nonNull(userInfo = dataService.getUserInfo(id)))) {
                    action = redisService.setUserInfo(userInfo);
                }
            }

        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_SOURCE_SERVICE.error("get userInfo from source service, id:{}, error:{}", id, ex);
        } finally {
            LogBackToolkit.LOGGER_SOURCE_SERVICE.info("get userInfo from source service, id:{}, action:{}", id, action);
        }

        /*
         * return
         */
        return userInfo;
    }
}