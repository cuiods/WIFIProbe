package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.UserVo;
import com.codingfairy.exception.ServerException;

/**
 * Created by cuihao on 2017-05-16.
 * auth service
 */
public interface UserService {
    UserVo login(String username, String password) throws ServerException;
}
