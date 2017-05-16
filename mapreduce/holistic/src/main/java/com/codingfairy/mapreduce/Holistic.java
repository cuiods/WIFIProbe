package com.codingfairy.mapreduce;

import com.codingfairy.mapreduce.classify.CustomerKeyCombiner;
import com.codingfairy.mapreduce.classify.CustomerKeyMapper;
import com.codingfairy.mapreduce.classify.CustomerKeyReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME ;

    public static void main(String[] args) throws Exception {
        final String inputFilePath = args[0];
        final String outputFilePath = args[1];
        final Long startTime = args.length>2?Long.parseLong(args[2]):0L;

        Configuration conf = new Configuration();
        conf.setLong(START_TIME, startTime);

        Job jobClassify = Job.getInstance(conf, "word count");
        jobClassify.setJarByClass(Holistic.class);
        jobClassify.setMapperClass(CustomerKeyMapper.class);
        jobClassify.setCombinerClass(CustomerKeyCombiner.class);
        jobClassify.setReducerClass(CustomerKeyReducer.class);
        jobClassify.setOutputKeyClass(Text.class);
        jobClassify.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(jobClassify, new Path(inputFilePath));
        FileOutputFormat.setOutputPath(jobClassify, new Path(outputFilePath));
        System.exit(jobClassify.waitForCompletion(true) ? 0 : 1);
    }
}
