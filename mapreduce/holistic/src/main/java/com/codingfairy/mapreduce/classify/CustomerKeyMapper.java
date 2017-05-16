package com.codingfairy.mapreduce.classify;

import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.ProbeJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by darxan on 2017/5/16.
 */
public class CustomerKeyMapper extends Mapper<Object, Text, Text, PhoneJson> {

    private Gson gson = new Gson();
    private Text phoneAsKey = new Text();

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        StringTokenizer stringTokenizer = new StringTokenizer(value.toString());


        while (stringTokenizer.hasMoreTokens()) {

            List<ProbeJson> probeJsons = gson.fromJson(
                    stringTokenizer.nextToken(), new TypeToken<List<ProbeJson>>(){}.getType());

            for (ProbeJson prob:probeJsons) {

                long time = -1L;
                try {
                    time = Long.parseLong(prob.getTime());
                }catch (Exception e) {
                }

                if (time>=0) {
                    for (PhoneJson phoneJson:prob.getData()) {
                        phoneJson.setTime(time);
                        phoneAsKey.set(phoneJson.getMac());
                        context.write(phoneAsKey, phoneJson);
                    }
                }

            }
        }
    }
}
