package com.codingfairy.data.daoImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.data.dao.UserDao;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * dao test
 */
public class UserDaoImplTest extends BaseTest{

    @Resource
    private UserDao userDao;

    @Test
    public void findById() throws Exception {
        System.out.println(userDao.findById(1).getUsername());
    }

    @Test
    public void findByName() throws Exception {
        System.out.println(userDao.findByName("admin"));
    }

    @Test
    @Rollback
    @Transactional
    public void save() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}