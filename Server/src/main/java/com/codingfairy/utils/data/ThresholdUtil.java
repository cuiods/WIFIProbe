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
}
