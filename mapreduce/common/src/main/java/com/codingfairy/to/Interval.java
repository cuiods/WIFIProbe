package com.codingfairy.to;

import com.codingfairy.vo.PhoneJson;
import lombok.Data;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by darxan on 2017/5/16.
 */
@Data
public class Interval{


    private int lower;
    private int upper;

    public Interval(int lower, int upper) {
        this.upper = upper;
        this.lower = lower;
    }


}
