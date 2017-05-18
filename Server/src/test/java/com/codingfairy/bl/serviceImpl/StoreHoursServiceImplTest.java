package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.StoreHoursService;
import com.codingfairy.utils.enums.QueryThreshold;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 *
 */
public class StoreHoursServiceImplTest extends BaseTest {

    @Resource
    private StoreHoursService storeHoursService;

    @Test
    public void getStoreHoursStat() throws Exception {
        System.out.println(storeHoursService
                .getStoreHoursStat((int)(System.currentTimeMillis()/(3600*1000)-1), QueryThreshold.HOUR,10,null));
    }

    @Test
    public void findByHourAndProbe() throws Exception {
        System.out.println(storeHoursService.findByHourAndProbe((int)(System.currentTimeMillis()/(3600*1000)-1),"1s12sz"));
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}