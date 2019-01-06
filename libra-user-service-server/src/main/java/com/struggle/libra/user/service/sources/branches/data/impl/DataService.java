package com.struggle.libra.user.service.sources.branches.data.impl;

import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.mybatis.UserDao;
import com.struggle.libra.user.service.sources.branches.data.IDataService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service(value = "dataService")
public class DataService implements IDataService {

    @Resource(name = "userDao")
    private UserDao userDao;

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
            action = action && Objects.nonNull(userInfo = userDao.getUserInfo(id));

        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_SOURCE_DATA_SERVICE.error("get userInfo from data service, id:{}, error:{}", id, ex);
        } finally {
            LogBackToolkit.LOGGER_SOURCE_DATA_SERVICE.info("get userInfo from data service, id:{}, action:{}", id, action);
        }

        /*
         * return
         */
        return userInfo;
    }
}
