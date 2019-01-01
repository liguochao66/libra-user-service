package com.struggle.libra.user.service.branches;

import com.struggle.libra.user.service.LibraUserServiceLocator;
import com.struggle.libra.user.service.partner.IPartnerService;

public class LibraUserServiceELocator extends LibraUserServiceLocator {

    public static IPartnerService getPartnerService() {
        return getDubboServiceProxy("partnerService",IPartnerService.class);
    }
}