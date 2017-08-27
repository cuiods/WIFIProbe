package com.codingfairy.mapreduce.config;

/**
 * Created by darxan on 17-5-20.
 */
public class ActivenessJudge {

    public static boolean isHigh(Long l) {
        return l<1000*60*60*2;
    }

    public static boolean isMedian(Long l) {
        return l<1000*60*60*24;
    }

    public static boolean isLow(Long l) {
        return l<1000*60*60*24*3;
    }

}
