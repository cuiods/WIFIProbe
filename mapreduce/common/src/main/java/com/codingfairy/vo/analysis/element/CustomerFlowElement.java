package com.codingfairy.vo.analysis.element;

import lombok.Data;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by darxan on 2017/5/14.
 *
 * 店铺或区域整体客流及趋势
 * CustomerFlow是对客流量的数据表示。
 *
 * 横坐标表示某个时间点（某整点毫秒）（时间点之间的形成的时间段）
 * 总总表表示某个时间段的客流量
 */
@Data
public class CustomerFlowElement implements Writable {

    public void write(DataOutput dataOutput) throws IOException {
        Text text = new Text(wifiProb==null?"":wifiProb);
        text.write(dataOutput);

        IntWritable intWritable = new IntWritable();

        intWritable.set(inNoOutWifi);
        intWritable.write(dataOutput);
        intWritable.set(inNoOutStore);
        intWritable.write(dataOutput);

        intWritable.set(outNoInWifi);
        intWritable.write(dataOutput);
        intWritable.set(outNoInStore);
        intWritable.write(dataOutput);

        intWritable.set(inAndOutWifi);
        intWritable.write(dataOutput);
        intWritable.set(inAndOutStore);
        intWritable.write(dataOutput);

        intWritable.set(stayInWifi);
        intWritable.write(dataOutput);
        intWritable.set(stayInStore);
        intWritable.write(dataOutput);

        DoubleWritable doubleWritable = new DoubleWritable();
        doubleWritable.set(jumpRate);
        doubleWritable.write(dataOutput);
        doubleWritable.set(deepVisit);
        doubleWritable.write(dataOutput);
        doubleWritable.set(inStoreRate);
        doubleWritable.write(dataOutput);
    }



    public void readFields(DataInput dataInput) throws IOException {
        Text text = new Text();
        text.readFields(dataInput);
        wifiProb = text.toString();

        IntWritable intReader = new IntWritable();

        intReader.readFields(dataInput);
        inNoOutWifi = intReader.get();
        intReader.readFields(dataInput);
        inNoOutStore = intReader.get();

        intReader.readFields(dataInput);
        outNoInWifi = intReader.get();
        intReader.readFields(dataInput);
        outNoInStore = intReader.get();


        intReader.readFields(dataInput);
        inAndOutWifi = intReader.get();
        intReader.readFields(dataInput);
        inAndOutStore = intReader.get();

        intReader.readFields(dataInput);
        stayInWifi = intReader.get();
        intReader.readFields(dataInput);
        stayInStore = intReader.get();


        DoubleWritable doubleWritable = new DoubleWritable();
        doubleWritable.readFields(dataInput);
        jumpRate = doubleWritable.get();
        doubleWritable.readFields(dataInput);
        deepVisit = doubleWritable.get();
        doubleWritable.readFields(dataInput);
        inStoreRate = doubleWritable.get();

    }

    protected String wifiProb;
    private Long hour;

    private int inNoOutWifi;
    private int inNoOutStore;
    private int outNoInWifi;
    private int outNoInStore;
    private int inAndOutWifi;
    private int inAndOutStore;
    private int stayInWifi;
    private int stayInStore;

    private double jumpRate;//
    private double deepVisit;//深访率：进⼊店铺深度访问的顾客及占⽐(占总体客流)
    private double inStoreRate;//进入店铺或区域的客流占全部客流的比例及趋势
    private double total;//which is 1

    public int getTotalInWifi() {
        return inNoOutWifi+inAndOutWifi;
    }

    public int getTotalOutWifi() {
        return outNoInWifi+inAndOutWifi;
    }

    public int getTotalInStore() {
        return inNoOutStore+inAndOutStore;
    }

    public int getTotalOutStore() {
        return outNoInStore+inAndOutStore;
    }


    public void addAnother(CustomerFlowElement another) {
        inNoOutWifi+=another.inNoOutWifi;
        inNoOutStore+=another.inNoOutStore;

        inAndOutWifi+=another.inAndOutWifi;
        inAndOutStore+=another.inAndOutStore;

        outNoInWifi+=another.outNoInWifi;
        outNoInStore+=another.outNoInStore;

        stayInWifi+=another.stayInWifi;
        stayInStore+=another.stayInStore;

        jumpRate+=another.jumpRate;
        deepVisit+=another.deepVisit;
        inStoreRate+=another.inStoreRate;
        total+=another.total;
    }
}
