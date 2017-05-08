package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.cache.ConcurrentDataList;
import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.web.json.ProbeJson;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * receiver service test
 */
public class ReceiverServiceImplTest extends BaseTest{

    @Resource
    private ReceiverService receiverService;

    @Test
    public void receive() throws Exception {
        receiverService.receive(new ProbeJson());
        Thread.sleep(1000);
        System.out.println(ConcurrentDataList.instance().getAll().size());
    }

    @Test
    public void commit() throws Exception {
    }

    @Test
    public void statLatest() throws Exception {
    }

}