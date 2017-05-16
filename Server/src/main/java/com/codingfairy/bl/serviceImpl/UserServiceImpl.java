package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.UserService;
import com.codingfairy.bl.vo.UserVo;
import com.codingfairy.data.dao.UserDao;
import com.codingfairy.data.entity.UserEntity;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-05-16.
 * user service impl
 */
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserVo login(String username, String password) throws ServerException {
        UserEntity userEntity = userDao.findByName(username);
        if (userEntity==null) throw new ServerException(ServerCode.USER_NOT_FOUND);
        if (!userEntity.getPassword().equals(password)) throw new ServerException(ServerCode.ERROR_PASSWORD);
        return new UserVo(userEntity);
    }
}
