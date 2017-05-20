package com.codingfairy.mapreduce;

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
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME = "START_TIME";


    public static void classify( final String inputFilePath,
                                 final String outputFilePath,
                                 final Long startTime) throws Exception{

        Configuration conf = new Configuration();
        conf.setLong(START_TIME, startTime);


        Job jobClassify = Job.getInstance(conf, "classify");

        jobClassify.setJarByClass(Holistic.class);

        jobClassify.setMapperClass(CustomerKeyMapper.class);
        jobClassify.setReducerClass(CustomerKeyReducer.class);
        jobClassify.setCombinerClass(CustomerKeyCombiner.class);


        jobClassify.setOutputKeyClass(Text.class);
        jobClassify.setOutputValueClass(Text.class);

        jobClassify.setMapOutputKeyClass(Text.class);
        jobClassify.setMapOutputValueClass(PhoneJson.class);


        FileInputFormat.addInputPath(jobClassify, new Path(inputFilePath));
        FileOutputFormat.setOutputPath(jobClassify, new Path(outputFilePath));


        System.exit(jobClassify.waitForCompletion(true) ? 0 : 1);
    }


    public static void analyze(final String inputFilePath,
                               final String outputFilePath,
                               final Long startTime) throws Exception {
        Configuration conf = new Configuration();
        conf.setLong(START_TIME, startTime);

        Job jobAnalyze = Job.getInstance(conf, "analyze");

        jobAnalyze.setJarByClass(Holistic.class);

        jobAnalyze.setMapperClass(AnalysisMapper.class);
        jobAnalyze.setReducerClass(AnalysisReducer.class);
        jobAnalyze.setCombinerClass(AnalysisCombiner.class);

        jobAnalyze.setOutputKeyClass(KeyWrapper.class);
        jobAnalyze.setOutputValueClass(Text.class);

        jobAnalyze.setMapOutputKeyClass(KeyWrapper.class);
        jobAnalyze.setMapOutputValueClass(ValueWrapper.class);

        FileInputFormat.addInputPath(jobAnalyze, new Path(inputFilePath));
        FileOutputFormat.setOutputPath(jobAnalyze, new Path(outputFilePath));

        System.exit(jobAnalyze.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        if (args.length>=2) {
            final String inputFilePath = args[0];
            final String outputFilePath = args[1];
            final Long startTime = args.length>2?Long.parseLong(args[2]):0L;
            analyze(inputFilePath, outputFilePath, startTime);
        }
    }
}
