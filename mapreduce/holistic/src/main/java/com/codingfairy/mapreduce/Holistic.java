package com.codingfairy.mapreduce;

import com.codingfairy.mapreduce.classify.CustomerKeyCombiner;
import com.codingfairy.mapreduce.classify.CustomerKeyMapper;
import com.codingfairy.mapreduce.classify.CustomerKeyReducer;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME = "START_TIME";

    public static void main(String[] args) throws Exception {
        final String inputFilePath = args[0];
        final String outputFilePath = args[1];
        final Long startTime = args.length>2?Long.parseLong(args[2]):0L;

        Logger.println("initial-----------------------------");
        Logger.println("input    : "+args[0]);
        Logger.println("output   : "+args[1]);
        Logger.println("sta_time : "+args[2]);
        Logger.println("initial-----------------------------");

        Configuration conf = new Configuration();
        conf.setLong(START_TIME, startTime);


        Logger.println("initial configuration");
        Job jobClassify = Job.getInstance(conf, "classify");
        Logger.println("setting job name");
        jobClassify.setJarByClass(Holistic.class);
        Logger.println("set jar main class : "+Holistic.class);
        jobClassify.setMapperClass(CustomerKeyMapper.class);
        Logger.println("set mapper");
        jobClassify.setReducerClass(CustomerKeyReducer.class);
        Logger.println("set reducer");
        jobClassify.setCombinerClass(CustomerKeyCombiner.class);
        Logger.println("set combiner");


        jobClassify.setOutputKeyClass(Text.class);
        Logger.println("set output key:");
        jobClassify.setOutputValueClass(Text.class);
        Logger.println("set output value");

        jobClassify.setMapOutputKeyClass(Text.class);
        Logger.println("set map output key");
        jobClassify.setMapOutputValueClass(PhoneJson.class);
        Logger.println("set map output value");


        FileInputFormat.addInputPath(jobClassify, new Path(inputFilePath));
        Logger.println("set input path");
        FileOutputFormat.setOutputPath(jobClassify, new Path(outputFilePath));
        Logger.println("set output path");

        Logger.println("=============================");
        Logger.println("waiting job.....");
        System.exit(jobClassify.waitForCompletion(true) ? 0 : 1);
    }
}
