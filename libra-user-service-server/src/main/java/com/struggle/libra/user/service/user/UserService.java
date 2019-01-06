package com.struggle.libra.user.service.user;

import com.struggle.libra.user.service.entities.model.user.UserInfo;
import com.struggle.libra.user.service.sources.ISourcesService;
import com.struggle.libra.user.service.toolkits.LogBackToolkit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Objects;

/**
 * @author james.li
 */
@Path("usr")
@Service(value = "userService")
public class UserService implements IUserService {

    @Resource(name = "sourcesService")
    private ISourcesService sourcesService;

    @GET
    @Override
    @Path("get")
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
            action = action && Objects.nonNull(userInfo = sourcesService.getUserInfo(id));

        } catch (Exception ex) {
            action = false;
            LogBackToolkit.LOGGER_USER_SERVICE.error("get userInfo from source service, id:{}, error:{}", id, ex);
        } finally {
            LogBackToolkit.LOGGER_USER_SERVICE.info("get userInfo from source service, id:{}, action:{}", id, action);
        }

        /*
         * return
         */
        return userInfo;
    }
}