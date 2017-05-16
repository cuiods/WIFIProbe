package com.codingfairy.mapreduce.flow;

import com.codingfairy.vo.PhoneJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by darxan on 2017/5/16.
 */
public class AnalysisMapper extends Mapper<Text, Text, Text, IntWritable> {

    private Gson gson = new Gson();

    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {

        StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
        while (stringTokenizer.hasMoreTokens()) {
            String mac = stringTokenizer.nextToken();
            List<PhoneJson> phoneData = gson.fromJson(
                    stringTokenizer.nextToken(), new TypeToken<List<PhoneJson>>(){}.getType());

        }
    }
}
