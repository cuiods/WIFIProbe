package com.codingfairy.mapreduce.config;

/**
 * Created by darxan on 17-5-20.
 */
public class IntervalCalculator {

    public static long cycleMask = 1000*60*10;
    public static long getCycleInterval(long cycle) {
        return cycle/cycleMask*cycleMask;
    }

    public static long inStoreMask = 1000860810;
    public static long getInStoreInterval(long inStoreTime) {
        return inStoreTime*inStoreTime/inStoreMask;
    }
}
