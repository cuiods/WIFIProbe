package com.codingfairy.bl.serviceImpl;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cuihao on 2017-05-20.
 */
public class TimeTest {

    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,4,1);
        System.out.println(calendar.getTimeInMillis()/(3600*1000));
    }
}
