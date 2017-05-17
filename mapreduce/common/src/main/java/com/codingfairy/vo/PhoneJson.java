package com.codingfairy.vo;

import lombok.Data;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
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
       writeChars(out,mac);
       writeChars(out,rssi);
       writeChars(out,range);
       writeChars(out,ts);
       writeChars(out,tmc);
       writeChars(out,tc);
       writeChars(out,ds);
       writeChars(out,essid0);
       writeChars(out,essid1);
       writeChars(out,essid2);
       writeChars(out,essid3);
       writeChars(out,essid4);
       writeChars(out,essid5);
       writeChars(out,essid6);
       writeLong(out, time);
    }

    private Text text = new Text();
    private LongWritable longWritable = new LongWritable();
    
    private void writeChars(DataOutput out, String value) throws IOException {
        if (value!=null) {
            text.set(value);
        }else {
            text.set("");
        }
        text.write(out);
    }

    private void writeLong(DataOutput out, long value) throws IOException {
        longWritable.set(value);
        longWritable.write(out);
    }
    
    private String readChars(DataInput in) throws IOException{
        text.readFields(in);
        return text.toString();
    }

    private long readLong(DataInput in) throws IOException{
        longWritable.readFields(in);
        return longWritable.get();
    }

    public void readFields(DataInput in) throws IOException {
        mac =   readChars(in);
        rssi = readChars(in);
        range = readChars(in);
        ts = readChars(in);
        tmc = readChars(in);
        tc = readChars(in);
        ds = readChars(in);
        essid0 = readChars(in);
        essid1 = readChars(in);
        essid2 = readChars(in);
        essid3 = readChars(in);
        essid4 = readChars(in);
        essid5 = readChars(in);
        essid6 = readChars(in);
        time = readLong(in);
    }
}
