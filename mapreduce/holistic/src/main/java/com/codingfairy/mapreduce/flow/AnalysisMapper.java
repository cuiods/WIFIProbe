package com.codingfairy.mapreduce.flow;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataCombiner;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darxan on 2017/5/16.
 */
public class AnalysisMapper extends Mapper<Text, Text, Text, IntWritable> {

    private Gson gson = new Gson();

    //00:23:89:30:89:91
    private Pattern pattern = Pattern.compile("(([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2})(\\s+)(.*)");

    private long startTime = -1;
    private static int count = 0;
    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {

        Logger.println("map count: " + count++);
        Logger.println(key);
        if (startTime<0) {
            Logger.println("first time to initial");
            startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
            Logger.println("initial start time");
        }

        Matcher matcher = pattern.matcher(value.toString());
        if (matcher.find()) {
            String mac = matcher.group(1);
            String dataString = matcher.group(4);
            List<PhoneJson> phoneData = gson.fromJson(
                    dataString, new TypeToken<List<PhoneJson>>(){}.getType());

        }

    }
}
