package com.struggle.libra.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class LibraUserServiceProvider {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(LibraUserServiceProvider.class);

    /**
     * application name
     */
    private final static String applicationName = "libra-user-service-server";

    /**
     * application content file path
     */
    private final static String applicationContextFilePath = "com/struggle/libra/user/service/applicationContext.xml";

    /**
     * application content init
     *
     * @param args parameter collection
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {

        /*
         * spring init
         */
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(applicationContextFilePath);
        context.start();

        /*
         * logger
         */
        if (logger.isDebugEnabled()) {
            logger.debug("{} is starting!!!!!", applicationName);
        }

        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            /*
             * logger
             */
            logger.debug("{} is stopping!!!!!", applicationName);

            /*
             * spring destroy
             */
            context.destroy();

            /*
             * count down
             */
            latch.countDown();
        }));

        latch.await();

        if (logger.isDebugEnabled()) {
            logger.debug("{} is stopped!!!!!", applicationName);
        }

    }
}