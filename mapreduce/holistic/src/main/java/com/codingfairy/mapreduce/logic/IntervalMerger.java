package com.codingfairy.mapreduce.logic;

import com.codingfairy.to.Interval;
import com.codingfairy.vo.PhoneJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 17-5-17.
 */
public class IntervalMerger {



    public static List<Interval> merge(List<PhoneJson> list, int count) {
        if (list==null||list.size()==0) {
            return null;
        }

        List<Interval> intervals = new ArrayList<Interval>(Math.max(count, 8));
        int lastUpper = 0;
        Interval lastInterval = new Interval(0, 0);
        intervals.add(lastInterval);
        for (int current=1; current<list.size(); current++) {

            if (list.get(current).getTime()-list.get(lastUpper).getTime()>10000) {
                Interval newInterval = new Interval(current, current);
                intervals.add(newInterval);
                lastInterval = newInterval;
            }else {
                lastInterval.setUpper(current);

            }
            lastUpper = current;
        }

        return intervals;
    }
}
