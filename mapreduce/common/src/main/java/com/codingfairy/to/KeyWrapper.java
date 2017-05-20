package com.codingfairy.to;

import lombok.Data;
import org.apache.hadoop.io.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by darxan on 17-5-20.
 */
@Data
public class KeyWrapper implements WritableComparable<KeyWrapper> {


    private Text type;

    private LongWritable millisTime;

    public int compareTo(KeyWrapper keyWrapper) {
        int compare = type.compareTo(keyWrapper.getType());

        if (compare==0) {
            return millisTime.compareTo(keyWrapper.getMillisTime());
        }

        return compare;
    }

    public void write(DataOutput dataOutput) throws IOException {
        if (type==null) {
            type = new Text("");
        }
        type.write(dataOutput);

        if (millisTime==null)
            millisTime = new LongWritable(-1L);
        millisTime.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        type = new Text();
        type.readFields(dataInput);

        millisTime = new LongWritable();
        millisTime.readFields(dataInput);
    }

}
