package com.codingfairy.bl.serviceImpl;

import com.codingfairy.BaseTest;
import com.codingfairy.bl.service.ProbeService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-05-17.
 */
public class ProbeServiceImplTest extends BaseTest {

    @Resource
    private ProbeService probeService;

    @Test
    public void findAll() throws Exception {
        System.out.println(probeService.findAll(0,10).getContent());
    }

    @Test
    public void findById() throws Exception {
        System.out.println(probeService.findById(1));
    }

}