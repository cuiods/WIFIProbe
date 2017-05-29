package com.codingfairy.mapreduce.flow;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.mapreduce.logic.IntervalCalculator;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by darxan on 17-5-20.
 */
public class MapperWriter {

    private AnalysisMapper.Context context;
    private HourStatistic statistic;


    public MapperWriter(Mapper.Context context, HourStatistic statistic) {
        this.context = context;
        this.statistic = statistic;
    }


    public void write() throws IOException, InterruptedException {

        writeCycle();
        writeInStoreHour();
        writCustomerFlow();
        writNewOldCustomer();

    }



    private void writNewOldCustomer() throws IOException, InterruptedException {

        KeyWrapper newOldKey = new KeyWrapper();
        newOldKey.setType(new Text(MapKeyConfig.NEW_OLD_CUSTOMER));

        LongWritable longWritable = new LongWritable();
        newOldKey.setMillisTime(longWritable);

        for (NewOldCustomElement newOldCustomElement : statistic.getNewOldCustomElements()) {
            longWritable.set(newOldCustomElement.getHour());
            context.write(newOldKey, new ValueWrapper(newOldCustomElement));
        }
    }



    private void writCustomerFlow() throws IOException, InterruptedException{

        KeyWrapper customerFlowKey = new KeyWrapper();
        customerFlowKey.setType(new Text(MapKeyConfig.CUSTOMER_FLOW_KEY));

        LongWritable longWritable = new LongWritable();
        customerFlowKey.setMillisTime(longWritable);


        for (CustomerFlowElement customerFlowElement:statistic.getCustomerFlowElements()) {
            longWritable.set(customerFlowElement.getHour());
            context.write(customerFlowKey, new ValueWrapper(customerFlowElement));
        }
    }

    private void writeInStoreHour() throws IOException, InterruptedException{

        KeyWrapper cycleKey = new KeyWrapper();
        cycleKey.setType(new Text(MapKeyConfig.IN_STORE_HOUR));

        LongWritable longWritable = new LongWritable();
        cycleKey.setMillisTime(longWritable);

        IntWritable value = new IntWritable(1);


        List<Long> inStoreHours = statistic.getInStoreHours();
        for (Long inStoreTime : inStoreHours) {
            longWritable.set(IntervalCalculator.getInStoreInterval(inStoreTime));
            context.write(cycleKey, new ValueWrapper(value));
        }
    }


    private void writeCycle() throws IOException, InterruptedException{

        KeyWrapper cycleKey = new KeyWrapper();
        cycleKey.setType(new Text(MapKeyConfig.CYCLE));

        LongWritable longWritable = new LongWritable();
        cycleKey.setMillisTime(longWritable);

        IntWritable value = new IntWritable(1);

        for (Long cycle : statistic.getCycles()) {
            longWritable.set(IntervalCalculator.getCycleInterval(cycle));
            context.write(cycleKey, new ValueWrapper(value));
        }
    }
}
