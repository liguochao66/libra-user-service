package com.struggle.libra.user.service;

import com.caishi.capricorn.common.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraUserServiceLocator {

    private final static String applicationName = "libra-user-service";

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(LibraUserServiceLocator.class);

    /**
     * application context file path
     */
    private final static String applicationContextFilePath = "com/struggle/libra/user/service/applicationContext.xml";

    /**
     * application context
     */
    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(applicationContextFilePath);

    /**
     * 获取Dubbo应用服务接口代理
     *
     * @param serviceName 服务接口名称
     * @param <T>         服务接口类型
     * @return Dubbo应用服务接口代理
     */
    protected static <T> T getDubboServiceProxy(String serviceName, Class<T> entityClass) {

        /*
         * data init
         */
        Object object = null;
        boolean action = true;
        T dubboServiceProxy = null;

        /*
         * execute
         */
        try {

            /*
             * execute
             */
            action = logger != null;
            action = action && !DataUtils.isEmpty(serviceName);
            action = action && !DataUtils.isEmpty(applicationName);
            action = action && entityClass != null && entityClass.isInterface();
            action = action && (object = applicationContext.getBean(serviceName)) != null;
            if (action) {
                dubboServiceProxy = entityClass.cast(object);
            }

        } catch (Exception ex) {
            action = false;
            logger.error("get {} from {} met error, error:{}", serviceName, applicationName, ex);
        } finally {
            if (logger != null) {
                logger.info("get {} from {}, action:{}", serviceName, applicationName, action);
            }
        }

        /*
         * return
         */
        return dubboServiceProxy;
    }
}