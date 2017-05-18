package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.ActivenessService;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class ActivenessServiceImplTest extends BaseTest {

    @Resource
    private ActivenessService activenessService;

    @Test
    public void getActivenessStat() throws Exception {
        System.out.println(activenessService
                .getActivenessStat((int)(System.currentTimeMillis()/(3600*1000)), QueryThreshold.HOUR,10,null));
    }

    @Test
    public void findByHourAndProbe() throws Exception {
        System.out.println(activenessService.findByHourAndProbe((int)(System.currentTimeMillis()/(3600*1000)),"1s12sz"));
    }

    @Test
    public void findById() throws Exception {
        System.out.println(activenessService.findById(1));
    }

    @Test
    public void save() throws Exception {
    }

}