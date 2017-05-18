package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.NewOldService;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class NewOldServiceImplTest extends BaseTest {

    @Resource
    private NewOldService newOldService;

    @Test
    public void getNewOldStat() throws Exception {
        System.out.println(newOldService
                .getNewOldStat((int)(System.currentTimeMillis()/(3600*1000)-1), QueryThreshold.HOUR,10,null));
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