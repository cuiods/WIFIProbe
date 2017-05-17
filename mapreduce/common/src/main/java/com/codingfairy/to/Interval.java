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
public class Interval implements Writable{


    private PhoneJson lower;
    private PhoneJson upper;

    public Interval(PhoneJson lower, PhoneJson upper) {
        this.upper = upper;
        this.lower = lower;
    }

    public void write(DataOutput out) throws IOException {
        lower.write(out);
        upper.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        lower.readFields(in);
        upper.readFields(in);
    }
}
