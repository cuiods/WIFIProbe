package com.codingfairy.mapreduce.classify;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataCombiner;
import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;

/**
 * Created by darxan on 2017/5/16.
 */
public class CustomerKeyCombiner extends Reducer<Text,PhoneJson,Text,ObjectWritable> {

    private Long startTime = -1L;
    private ObjectWritable objectValue = new ObjectWritable();
    private PhoneDataCombiner intervals;

    @Override
    protected void reduce(Text key, Iterable<PhoneJson> values, Context context)
            throws IOException, InterruptedException {
        if (startTime<0) {
            startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
            intervals = new PhoneDataCombiner(startTime);
        }

        List<PhoneJson> phonesData = intervals.getPhonesData(values);
        if (phonesData!=null&&phonesData.size()>0) {
            objectValue.set(phonesData);
            context.write(key, objectValue);
        }

    }
}
