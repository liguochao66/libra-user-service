package com.struggle.libra.user.service.user;

import com.struggle.libra.user.service.entities.user.UserInfo;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author james.li
 */
@Path("usr")
@Service(value = "userService")
public class UserService implements IUserService {

    @GET
    @Override
    @Path("get")
    public UserInfo getUserInfo(String id) {
        return null;
    }
}