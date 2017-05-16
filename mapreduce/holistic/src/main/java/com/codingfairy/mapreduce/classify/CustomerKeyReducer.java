package com.codingfairy.mapreduce.classify;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataCombiner;
import com.codingfairy.vo.PhoneJson;
import com.google.gson.Gson;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;

/**
 * Created by darxan on 2017/5/16.
 */
public class CustomerKeyReducer extends Reducer<Text,ObjectWritable,Text,Text> {

    private PhoneDataCombiner phoneDataCombiner ;
    private long startTime;
    private Gson gson = new Gson();
    private Text text = new Text();
    @Override
    protected void reduce(Text key, Iterable<ObjectWritable> values, Context context)
            throws IOException, InterruptedException {

        if (startTime<0) {
            startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
            phoneDataCombiner = new PhoneDataCombiner(startTime);
        }

        List<PhoneJson> intervalList = phoneDataCombiner.mergePhonesData(values);
        if (intervalList!=null&&intervalList.size()>0) {
            text.set(gson.toJson(intervalList));
            context.write(key, text);
        }
    }
}
