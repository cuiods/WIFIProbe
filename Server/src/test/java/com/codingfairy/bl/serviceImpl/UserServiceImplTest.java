package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.UserService;
import com.codingfairy.exception.ServerException;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class UserServiceImplTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void login() throws Exception {
        System.out.println(userService.login("admin","admin"));
        try {
            userService.login("admin","ha");
            fail();
        } catch (ServerException e) {
            System.out.println(e.getMessage());
        }
    }

}