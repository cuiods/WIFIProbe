package com.codingfairy.mapreduce.logic;


import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.htrace.fasterxml.jackson.databind.util.LinkedNode;

import java.util.*;

/**
 * Created by darxan on 2017/5/16.
 * 对数据按照时间先后排序.
 */
public class PhoneDataSorter {

    private long time;

    public PhoneDataSorter(long time) {
        this.time = time;
    }

    public PhoneDataSorter() {
        this(0);
    }

    private Comparator<PhoneJson> phoneJsonComparator =
            new Comparator<PhoneJson>() {
                public int compare(PhoneJson o1, PhoneJson o2) {
                    long x = o1.getTime();
                    long y = o2.getTime();
                    return (x < y) ? -1 : ((x == y) ? 0 : 1);
                }
    };

    public List<PhoneJson> getPhonesData(Iterable<PhoneJson> values) {

        List<PhoneJson> phoneList = new ArrayList<PhoneJson>();

        for (PhoneJson tempObject : values) {
            try {
                phoneList.add(tempObject.clone());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(phoneList, phoneJsonComparator);

        return phoneList;
    }

}
