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

    private WritableComparable writableComparable;

    public int compareTo(KeyWrapper keyWrapper) {
        int compare = type.compareTo(keyWrapper.getType());

        if (compare==0) {
            return writableComparable.compareTo(keyWrapper.getWritableComparable());
        }

        return compare;
    }

    public void write(DataOutput dataOutput) throws IOException {
        type.write(dataOutput);
        writableComparable.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        type.readFields(dataInput);
        writableComparable.readFields(dataInput);
    }

}
