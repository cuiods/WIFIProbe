package com.codingfairy.mapreduce.flow;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataExtractor;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darxan on 2017/5/16.
 */
public class AnalysisMapper extends Mapper<Object, Text, KeyWrapper, ValueWrapper> {

    private Gson gson;

    //00:23:89:30:89:91
    private Pattern pattern;
    private PhoneDataExtractor extractor ;
    private long startTime ;
    private static int count ;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Logger.println("first time to initial");
        startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
        Logger.println("initial start time");

        pattern =  Pattern.compile("(([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2})(\\s+)(.*)");
        extractor = new PhoneDataExtractor(startTime);
        gson = new Gson();
        count = 0;
    }

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        Logger.println("map count: " + count++);
        Logger.println(key);

        Matcher matcher = pattern.matcher(value.toString());
        if (matcher.find()) {

            String dataString = matcher.group(4);
            List<PhoneJson> phoneData = gson.fromJson(
                    dataString, new TypeToken<List<PhoneJson>>(){}.getType());
            HourStatistic hourStatistic = extractor.extract(phoneData);
            if (hourStatistic==null)
                return;
            MapperWriter mapperWriter = new MapperWriter(context, hourStatistic);
            mapperWriter.write();
        }

    }



}
