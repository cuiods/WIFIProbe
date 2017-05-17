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

        Logger.println("map one line: " + count++);
        if (value.getLength()==0) {
            Logger.println("empty lone");
            return;
        }
        List<ProbeJson> probeJsons = gson.fromJson(
                value.toString(), new TypeToken<List<ProbeJson>>(){}.getType());
        Logger.println("convert to java list, size =  "+probeJsons.size());
        Logger.println("for each prob in list, classify by mac");
        for (ProbeJson prob:probeJsons) {

            long time = -1L;
            try {
                Logger.println(time);
                time = DateFormatter.getMillis(prob.getTime());
            }catch (Exception e) {
                Logger.println("time format error!!");
            }

            if (time>=0) {
                for (PhoneJson phoneJson : prob.getData()) {
                    Logger.println("one  line phone  data : "+phoneJson.getMac());
                    phoneJson.setTime(time);
                    phoneAsKey.set(phoneJson.getMac());
                    context.write(phoneAsKey, phoneJson);
                    Logger.println("print line phone data : "+phoneJson.getMac());
                }
            }

        }
    }
}
