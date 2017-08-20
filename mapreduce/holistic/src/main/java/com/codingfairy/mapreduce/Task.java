package com.codingfairy.mapreduce;

import com.codingfairy.config.FileConfig;
import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.mapreduce.classify.CustomerKeyCombiner;
import com.codingfairy.mapreduce.classify.CustomerKeyMapper;
import com.codingfairy.mapreduce.classify.CustomerKeyReducer;
import com.codingfairy.mapreduce.flow.AnalysisCombiner;
import com.codingfairy.mapreduce.flow.AnalysisMapper;
import com.codingfairy.mapreduce.flow.AnalysisReducer;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.HDFSTool;
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

import java.io.IOException;

/**
 * Created by darxan on 17-5-22.
 *
 * 1. wifi数据放置位置：/wifi/data/
 * 2. wifi数据上传位置:/wifi/upload/
 * 3. wifi数据按照用户分类数据： /wifi/classify
 * 4. wifi数据统计结果数据：/wifi/statistic/hour
 *
 * procedure:
 * 1. merge
 * 2. classify
 * 3. statistic
 * 4. delete classify
 */
public class Task implements Runnable {


    private long time;

    private boolean result ;

    private String classifyPath ;
    private String statisticPath ;

    public Task(final long time) {
        this.time = time;
        this.classifyPath = FileConfig.classify+"/"+time;
        this.statisticPath = FileConfig.statistic+"/"+time;
    }

    public boolean isResult() {
        return result;
    }

    public void run() {
        Logger.println("task executing.....");
        clear();
        result = merge()&&classify()&&analyze();
    }


    private boolean merge() {
        try {
            Logger.println("merging upload smaller files");
            HDFSTool.concat(FileConfig.upload);
            Logger.println("merged upload smaller files");
            return true;
        }catch (IOException e) {
            Logger.println(e);
            return false;
        }
    }

    private boolean classify() {
        try {
            Logger.println("classifying ....");
            return classify(FileConfig.data, classifyPath, time);
        }catch (Exception e) {
            Logger.println(e);
            return false;
        }
    }

    private boolean analyze() {
        try {
            Logger.println("analyzing ....");
            return analyze(classifyPath, statisticPath, time);
        }catch (Exception e) {
            Logger.println(e);
            return false;
        }
    }

    private boolean clear() {

        try {
            Logger.println("clearing classify files");
            HDFSTool.emptyDirectory(FileConfig.classify);
            Logger.println("cleared classify files");
            return true;
        }catch (Exception e) {
            Logger.println(e);
            return false;
        }
    }

    public void execute() {
        run();
    }


    public boolean classify( final String inputFilePath,
                                 final String outputFilePath,
                                 final Long startTime) throws Exception{

        Configuration conf = new Configuration();
        conf.setLong(Holistic.START_TIME, startTime);


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


        return jobClassify.waitForCompletion(true);
    }


    public boolean analyze(final String inputFilePath,
                               final String outputFilePath,
                               final Long startTime) throws Exception {
        Configuration conf = new Configuration();
        conf.setLong(Holistic.START_TIME, startTime);

        Job jobAnalyze = Job.getInstance(conf, "analyze");

        jobAnalyze.setJarByClass(Holistic.class);

        MultipleOutputs.addNamedOutput(jobAnalyze, MapKeyConfig.NEW_OLD_CUSTOMER,
                TextOutputFormat.class, KeyWrapper.class, Text.class);
        MultipleOutputs.addNamedOutput(jobAnalyze, MapKeyConfig.CUSTOMER_FLOW_KEY,
                TextOutputFormat.class, KeyWrapper.class, Text.class);
        MultipleOutputs.addNamedOutput(jobAnalyze, MapKeyConfig.CYCLE,
                TextOutputFormat.class, KeyWrapper.class, Text.class);
        MultipleOutputs.addNamedOutput(jobAnalyze, MapKeyConfig.IN_STORE_HOUR,
                TextOutputFormat.class, KeyWrapper.class, Text.class);

        jobAnalyze.setMapperClass(AnalysisMapper.class);
        jobAnalyze.setReducerClass(AnalysisReducer.class);
        jobAnalyze.setCombinerClass(AnalysisCombiner.class);

        jobAnalyze.setOutputKeyClass(LongWritable.class);
        jobAnalyze.setOutputValueClass(Text.class);

        jobAnalyze.setMapOutputKeyClass(KeyWrapper.class);
        jobAnalyze.setMapOutputValueClass(ValueWrapper.class);

        FileInputFormat.addInputPath(jobAnalyze, new Path(inputFilePath));
        FileOutputFormat.setOutputPath(jobAnalyze, new Path(outputFilePath));

        return jobAnalyze.waitForCompletion(true) ;
    }
}
