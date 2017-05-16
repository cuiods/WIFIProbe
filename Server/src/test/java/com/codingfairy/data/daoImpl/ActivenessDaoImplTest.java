package com.codingfairy.data.daoImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.data.dao.ActivenessDao;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-16.
 * dao general test
 */
public class ActivenessDaoImplTest extends BaseTest {

    @Resource
    private ActivenessDao activenessDao;

    @Test
    public void getActivenessStat() throws Exception {
        long time = System.currentTimeMillis();
        int hour = (int) (time/(1000*60*60));
        System.out.println(activenessDao.getActivenessStat(hour, QueryThreshold.HOUR, 10, null));
    }

    @Test
    public void findByHourAndProbe() throws Exception {
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}