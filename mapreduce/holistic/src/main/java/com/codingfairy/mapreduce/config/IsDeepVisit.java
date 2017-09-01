package com.codingfairy.mapreduce.config;

/**
 * Created by darxan on 2017/9/1.
 */
public class IsDeepVisit {
    private static long DEEP_TIME = 1000L*60L*60L;

    public static boolean isDeepVisit(long inStoreTime) {
        return inStoreTime>DEEP_TIME;
    }
}
