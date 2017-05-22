package com.codingfairy.mapreduce;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.config.NodeConfig;
import com.codingfairy.mapreduce.classify.CustomerKeyCombiner;
import com.codingfairy.mapreduce.classify.CustomerKeyMapper;
import com.codingfairy.mapreduce.classify.CustomerKeyReducer;
import com.codingfairy.mapreduce.flow.AnalysisCombiner;
import com.codingfairy.mapreduce.flow.AnalysisMapper;
import com.codingfairy.mapreduce.flow.AnalysisReducer;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME = "START_TIME";



    public static void main(String[] args) {
        long time;
        try {
            time = Long.parseLong(args[0]);
        }catch (Exception e) {
            time = System.currentTimeMillis() - NodeConfig.MAX_WIFI_DATA_INTERVAL;
        }


        while (true) {
            try {
                long lastTime = time;
                time = System.currentTimeMillis() - NodeConfig.MAX_WIFI_DATA_INTERVAL;
                new Task(lastTime).execute();

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
