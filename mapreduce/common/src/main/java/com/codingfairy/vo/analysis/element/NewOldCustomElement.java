package com.codingfairy.vo.analysis.element;

import com.codingfairy.vo.analysis.NewOldCustom;
import lombok.Data;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by darxan on 2017/5/14.
 * 新老顾客：一定时间段内首次/两次以上进⼊店铺的顾客
 * 纵坐标：时间
 * 横坐标：新/老顾客数目
 */
@Data
public class NewOldCustomElement implements Writable {

    public void write(DataOutput dataOutput) throws IOException {
        Text text = new Text(wifiProb==null?"":wifiProb);
        text.write(dataOutput);

        LongWritable longWritable = new LongWritable();
        longWritable.set(hour);
        longWritable.write(dataOutput);
        longWritable.set(newCustomer);
        longWritable.write(dataOutput);
        longWritable.set(oldCustomer);
        longWritable.write(dataOutput);

    }

    public void readFields(DataInput dataInput) throws IOException {
        Text text = new Text();
        text.readFields(dataInput);
        wifiProb = text.toString();

        LongWritable reader = new LongWritable();

        reader.readFields(dataInput);
        hour = reader.get();

        reader.readFields(dataInput);
        newCustomer = (int)reader.get();

        reader.readFields(dataInput);
        oldCustomer = (int)reader.get();
    }

    protected String wifiProb;
    private Long hour;
    private int newCustomer;
    private int oldCustomer;

    public void addAnother(NewOldCustomElement another){
        newCustomer+=another.newCustomer;
        oldCustomer+=another.oldCustomer;
    }
}
