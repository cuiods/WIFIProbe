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

    public static void main(String[] args) {
        String string = "[{\"id\": \"007db104\",\"data\":[{\"mac\":\"f0:b4:29:76:96:1f\",\"rssi\":\"-68\",\"range\":\"129\",\"ts\":\"\",\"tmc\":\"\",\"tc\":\"\",\"ds\":\"\",\"essid0\":\"\",\"essid1\":\"\",\"essid2\":\"\",\"essid3\":\"\",\"essid4\":\"\",\"essid5\":\"\",\"essid6\":\"\",\"time\":1492595762000,\"text\":{\"bytes\":[49,50,57,98,52,58,50,57,58,55,54,58,57,54,58,49,102],\"length\":0},\"longWritable\":{\"value\":1492595762000}}],\"mmac\": \"20:f4:1b:7d:b1:04\",\"rate\": \"3\",\"time\": \"Wed Apr 19 17:56:02 2017\",\"lat\": \"\",\"lon\": \"\"}]";
        List<ProbeJson> probeJsons = new Gson().fromJson(
                string, new TypeToken<List<ProbeJson>>(){}.getType());
        System.out.println(probeJsons);
    }

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
                System.out.println(prob.getTime());
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
                    Logger.println("print line phone data : "+phoneJson.getTime());
                }
            }

        }
    }
}
