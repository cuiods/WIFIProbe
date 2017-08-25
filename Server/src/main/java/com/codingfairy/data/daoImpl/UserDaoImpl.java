package com.codingfairy.data.daoImpl;

import com.codingfairy.data.dao.UserDao;
import com.codingfairy.data.entity.UserEntity;
import com.codingfairy.data.repo.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * User dao impl
 * @author cuihao
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserEntity findByName(String name) {
        return userRepository.findByUsername(name);
}

    @Override
    public UserEntity findByNameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name,password);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}
