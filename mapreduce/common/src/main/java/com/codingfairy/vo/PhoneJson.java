package com.codingfairy.vo;

import lombok.Data;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Detected phone data json
 */
@Data
public class PhoneJson implements Writable{

    private String mac;
    private String rssi;
    private String range;
    private String ts;
    private String tmc;
    private String tc;
    private String ds;
    private String essid0;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;

    private long time;

    public void write(DataOutput out) throws IOException {
       new ObjectWritable(this).write(out);
    }

    public void readFields(DataInput in) throws IOException {
        new ObjectWritable(this).readFields(in);
    }
}
