package com.codingfairy.mapreduce.config;

import com.codingfairy.vo.PhoneJson;

/**
 * Created by darxan on 17-5-18.
 */
public class IsContinuous {

    public static long INTERVAL_MAX = 128*1000;

    public static boolean isContinuous(long time, PhoneJson phoneJson) {
        return isContinuous(time, phoneJson.getTime());
    }

    public static boolean isContinuous(long a, long b) {
        return Math.abs(a-b)<=INTERVAL_MAX;
    }

    public static boolean isContinuous(PhoneJson a, PhoneJson b) {
        return isContinuous(a.getTime(), b.getTime());
    }

}
