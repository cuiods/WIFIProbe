package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.FlowService;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class FlowServiceImplTest extends BaseTest {

    @Resource
    private FlowService flowService;

    @Test
    public void getFStat() throws Exception {
        System.out.println(flowService
                .getFStat((int)(System.currentTimeMillis()/(3600*1000)-1), QueryThreshold.HOUR,10,null));
    }

    @Test
    public void findByHourAndProbe() throws Exception {
        System.out.println(flowService.findByHourAndProbe((int)(System.currentTimeMillis()/(3600*1000)-1),"1s12sz"));
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}