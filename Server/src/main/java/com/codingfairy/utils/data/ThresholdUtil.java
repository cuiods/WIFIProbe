package com.codingfairy.utils.data;

import com.codingfairy.utils.enums.QueryThreshold;

/**
 * Created by cuihao on 2017-05-16.
 * convert threshold to date format
 */
public class ThresholdUtil {
    public static String convertToString(QueryThreshold queryThreshold) {
        StringBuilder thresholdStr = new StringBuilder();
        switch (queryThreshold) {
            case HOUR: thresholdStr.append(" %H");
            case DAY: thresholdStr.insert(0,"-%d");
            case MONTH: thresholdStr.insert(0,"-%m");
            case YEAR: thresholdStr.insert(0,"%Y");break;
            case WEEK: thresholdStr.append("%Y-%u"); break;
            default: thresholdStr.append("%Y-%m-%d");break;
        }
        return thresholdStr.toString();
    }

    public static String convertToPattern(QueryThreshold queryThreshold) {
        StringBuilder thresholdStr = new StringBuilder();
        switch (queryThreshold) {
            case HOUR: thresholdStr.append(" HH");
            case DAY: thresholdStr.insert(0,"-dd");
            case MONTH: thresholdStr.insert(0,"-MM");
            case YEAR: thresholdStr.insert(0,"yyyy");break;
            case WEEK: thresholdStr.append("yyyy-ww"); break;
            default: thresholdStr.append("yyyy-MM-dd");break;
        }
        return thresholdStr.toString();
    }
}
