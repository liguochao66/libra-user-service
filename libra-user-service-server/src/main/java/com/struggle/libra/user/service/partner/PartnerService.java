package com.struggle.libra.user.service.partner;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user/partner")
@Service(value = "partnerService")
public class PartnerService implements IPartnerService {

    @Override
    @GET
    @Path("register")
    public Boolean register() {
        return null;
    }
}