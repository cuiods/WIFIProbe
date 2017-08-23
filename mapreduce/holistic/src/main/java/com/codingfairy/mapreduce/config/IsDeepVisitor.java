package com.codingfairy.mapreduce.config;

/**
 * Created by darxan on 17-5-20.
 */
public class IsDeepVisitor {

    public static long MIN_DEEP_VISITOR_TIME = 1000*60*10;

    public static boolean isDeepVist(long time) {
        return time>=MIN_DEEP_VISITOR_TIME;
    }
}
