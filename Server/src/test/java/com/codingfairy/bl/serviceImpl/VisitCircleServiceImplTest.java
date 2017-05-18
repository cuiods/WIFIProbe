package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.VisitCircleService;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class VisitCircleServiceImplTest extends BaseTest {

    @Resource
    private VisitCircleService visitCircleService;

    @Test
    public void getVisitCircleStat() throws Exception {
        System.out.println(visitCircleService
                .getVisitCircleStat((int)(System.currentTimeMillis()/(3600*1000)-1), QueryThreshold.HOUR,10,null));
    }

    @Test
    public void findByHourAndProbe() throws Exception {
        System.out.println((int)(System.currentTimeMillis()/(3600*1000)-1));
        System.out.println(visitCircleService.findByHourAndProbe((int)(System.currentTimeMillis()/(3600*1000)-1),"1s12sz"));
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}