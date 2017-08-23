package com.codingfairy.mapreduce.flow;

import com.codingfairy.mapreduce.Holistic;
import com.codingfairy.mapreduce.logic.PhoneDataExtractor;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darxan on 2017/5/16.
 * in mapper:
 *   we read data which is classified bt mac address,
 *   then we analyze the data(customer flow, new or old customer, and so on)
 *   for each phone(identified by mac address).
 */
public class AnalysisMapper extends Mapper<Object, Text, KeyWrapper, ValueWrapper> {

    private Gson gson;

    //00:23:89:30:89:91 pattern for mac address
    private Pattern pattern;

    private PhoneDataExtractor extractor ;

    private long startTime ;

    public static void main(String[] args) {
        String mac = "20:f4:1b:7d:b8:33   fdfdfdf";
        Pattern pattern =  Pattern.compile("(([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2})(\\s+)(.*)");
        Matcher matcher = pattern.matcher(mac);
        matcher.find();

        for (int i = 0; i<=4; i++) {
            try {
                System.out.println(matcher.group(i));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Logger.println("first time to initial");
        startTime = context.getConfiguration().getLong(Holistic.START_TIME, 0L);
        Logger.println("initial start time: " + startTime);

        pattern =  Pattern.compile("(([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2})(\\s+)(.*)");
        extractor = new PhoneDataExtractor(startTime);
        gson = new Gson();
    }

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {


        Matcher matcher = pattern.matcher(value.toString());
        if (matcher.find()) {

            //找到对应于每一个mac地址的的所有数据
            String dataString = matcher.group(4);

            Logger.println("[mapper read]: read one line data identified by mac address: "
                    +matcher.group(1));
            List<PhoneJson> phoneData = gson.fromJson(
                    dataString, new TypeToken<List<PhoneJson>>(){}.getType());
            Logger.println("[mapper read]: convert one line data to phone data with size: "
                    +phoneData.size());

            Logger.debug("[mapper start statistic]: "+dataString);
            HourStatistic hourStatistic = extractor.extract(phoneData);
            Logger.debug("[mapper end statistic]: "+gson.toJson(hourStatistic));
            Logger.println("");

            if (hourStatistic==null) {
                return;
            }

            MapperWriter mapperWriter = new MapperWriter(context, hourStatistic);
            mapperWriter.write();
        }

    }



}
