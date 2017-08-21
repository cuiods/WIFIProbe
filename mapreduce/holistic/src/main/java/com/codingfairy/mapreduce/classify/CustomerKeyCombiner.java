package com.codingfairy.mapreduce.classify;

import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by darxan on 17-5-17.
 */
public class CustomerKeyCombiner extends Reducer<Text, PhoneJson, Text, PhoneJson> {

    private static int count = 0;

    @Override
    protected void reduce(Text key, Iterable<PhoneJson> values, Context context)
            throws IOException, InterruptedException {

        Logger.println("combiner one : "+ count++);
        super.reduce(key, values, context);

    }
}
