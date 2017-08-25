package com.codingfairy.data.dao;

import com.codingfairy.data.entity.UserEntity;

/**
 * User entity dao
 * @author cuihao
 */
public interface UserDao {
    UserEntity findById(int id);
    UserEntity findByName(String name);
    UserEntity findByNameAndPassword(String name,String password);
    UserEntity save(UserEntity userEntity);
    void delete(int id);
}
