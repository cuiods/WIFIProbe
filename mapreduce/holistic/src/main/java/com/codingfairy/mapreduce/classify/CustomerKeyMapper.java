package com.codingfairy.mapreduce.classify;

import com.codingfairy.tool.DateFormatter;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.ProbeJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by darxan on 2017/5/16.
 */
public class CustomerKeyMapper extends Mapper<Object, Text, Text, PhoneJson> {

    private Gson gson = new Gson();
    private Text phoneAsKey = new Text();

    private static int  count = 0;

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        Logger.println("[map read line]: " + count++);
        if (value.getLength()==0) {
            Logger.println("[map empty line]: empty line");
            return;
        }
        List<ProbeJson> probeJsons = gson.fromJson(
                value.toString(), new TypeToken<List<ProbeJson>>(){}.getType());
        Logger.println("[probes converted]: convert to java list, size =  "+probeJsons.size());

        for (ProbeJson prob:probeJsons) {

            long time = -1L;
            try {
                System.out.println(prob.getTime());
                time = DateFormatter.getMillis(prob.getTime());
            }catch (Exception e) {
                Logger.println("[warn]: time format error!!");
            }

            if (time>=0) {
                for (PhoneJson phoneJson : prob.getData()) {
                    Logger.println("[phone data]: one phone data with mac address: " +phoneJson.getMac() );
                    Logger.println("[phone data]: one phone data with time: " +phoneJson.getTime() );
                    phoneJson.setTime(time);
                    phoneAsKey.set(phoneJson.getMac());
                    context.write(phoneAsKey, phoneJson);
                    Logger.println("[map write to combiner]");
                }
            }

        }
    }
}
