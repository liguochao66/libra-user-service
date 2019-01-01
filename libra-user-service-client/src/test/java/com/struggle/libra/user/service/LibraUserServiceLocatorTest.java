package com.struggle.libra.user.service;

import com.caishi.capricorn.common.base.Production;
import com.struggle.libra.user.service.branches.LibraUserServiceELocator;
import org.junit.Assert;
import org.junit.Test;

public class LibraUserServiceLocatorTest {

    /*
     * 设置运行环境
     */
    static {
        Production.setProductionMode(Production.Mode.TEST);
    }

    @Test
    public void testGetPartnerService(){
        Assert.assertNotNull(LibraUserServiceELocator.getPartnerService());
    }
}
