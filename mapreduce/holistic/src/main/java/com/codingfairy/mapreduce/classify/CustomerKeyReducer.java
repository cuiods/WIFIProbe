package com.codingfairy.mapreduce.classify;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataSorter;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.google.gson.Gson;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;

/**
 * Created by darxan on 2017/5/16.
 */
public class CustomerKeyReducer extends Reducer<Text,PhoneJson,Text,Text> {

    private PhoneDataSorter sorter;
    private long startTime = -1L;
    private Gson gson = new Gson();
    private Text text = new Text();
    private static int count = 0;
    @Override
    protected void reduce(Text key, Iterable<PhoneJson> values, Context context)
            throws IOException, InterruptedException {

        Logger.println("[reduce count]: " + count++);
        Logger.println(key);
        if (startTime<0) {
            Logger.println("[INIT reducer] first time to initial");
            startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
            Logger.println("[INIT reducer] initial start time");
            sorter = new PhoneDataSorter(startTime);

        }

        List<PhoneJson> intervalList = sorter.getPhonesData(values);
        if (intervalList!=null&&intervalList.size()>0) {
            text.set(gson.toJson(intervalList));
            context.write(key, text);
            Logger.println("[SUCCESS] write one line result");
            Logger.println("[reducer write] : " + text.toString());
        }else {
            Logger.println("[ERROR] a empty address. wrong!!");
        }
    }
}
