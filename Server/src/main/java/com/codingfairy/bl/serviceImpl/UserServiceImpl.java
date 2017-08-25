package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.UserService;
import com.codingfairy.bl.vo.UserVo;
import com.codingfairy.data.dao.UserDao;
import com.codingfairy.data.entity.UserEntity;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-05-16.
 * user service impl
 */
@Service
@Slf4j
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

    @Override
    public UserVo register(String username, String password) throws ServerException {
        if(null != userDao.findByName(username)){
            throw new ServerException(ServerCode.USER_EXISTED);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setRole(RoleType.user);
        UserEntity user = userDao.save(userEntity);
        if(user==null){
            throw new ServerException(ServerCode.USER_SAVE_ERR);
        }
        return new UserVo(user);
    }

    @Override
    public UserVo changePassword(String username, String oldPassword, String newPassword) throws ServerException {
        UserEntity userEntity = userDao.findByNameAndPassword(username,oldPassword);
        log.info("userEntity is {}",userEntity);
        if(userEntity==null) throw new ServerException(ServerCode.ERROR_PASSWORD);
        userEntity.setPassword(newPassword);
        UserEntity user = userDao.save(userEntity);
        log.info("user is {}",user);
        if(user==null){
            throw new ServerException(ServerCode.USER_SAVE_ERR);
        }
        return new UserVo(user);
    }
}
