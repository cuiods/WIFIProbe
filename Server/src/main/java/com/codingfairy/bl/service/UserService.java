package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.UserVo;
import com.codingfairy.exception.ServerException;
import org.apache.catalina.Server;

/**
 * Created by cuihao on 2017-05-16.
 * auth service
 */
public interface UserService {
    UserVo login(String username, String password) throws ServerException;

    UserVo register(String username, String password) throws ServerException;

    UserVo changePassword(String username, String oldPassword, String newPassword) throws ServerException;
}
