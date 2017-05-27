package com.codingfairy.mapreduce.logic;

import com.codingfairy.mapreduce.logic.analyze.CustomerFlowAnalyze;
import com.codingfairy.mock.GsonTool;
import com.codingfairy.mock.MockData;
import com.codingfairy.to.Interval;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by darxan on 17-5-17.
 */
public class PhoneDataExtractor {

    /**
     * 传入参数：时间
     * > current 错误，按照异常处理，统计前2个小时的数据
     * < current >0 统计time到当前的数据
     * < 0       统计前（-time）个小时的数据 ，这里的单位是小时
     */
    final private long start_time;
    private long start_hour;
    private long current;


    /**
     * 需要统计多少个小时的数据
     */
    private int count;

    public PhoneDataExtractor(final long time) {
        this.current = System.currentTimeMillis();
        this.start_time = time>current?-2:time; //如果不符合规范，默认统计前2小时数据

    }


    private void calculate_hours(final long earliest) {


        if (start_time>=0){
            this.start_hour = (Math.max(start_time, earliest)/3600000)*3600000;
            this.count = (int) ((current-start_hour)/3600000);
        }else {
            //start_time<0表示的是统计当前截止的前（-start_time）个小时的数据
            this.start_hour = (current/3600000+start_time)*3600000;
            this.count = (int) -start_time;
        }
    }

    public HourStatistic extract(List<PhoneJson> phoneJsonList) {


        if (phoneJsonList==null&&phoneJsonList.size()==0) {
            return null;
        }

        calculate_hours(phoneJsonList.get(0).getTime());

        if (count<=0) {
            return null;
        }

        HourStatistic result = new HourStatistic();
        CustomerFlowAnalyze customerFlowElements
                = new CustomerFlowAnalyze(start_hour, count, phoneJsonList);
        customerFlowElements.analyze();

        result.setCustomerFlowElements(customerFlowElements.getCustomerFlowElements());
        result.setCycles(customerFlowElements.getCycles());
        result.setInStoreHours(customerFlowElements.getInStoreHours());
        result.setNewOldCustomElements(customerFlowElements.getNewOldCustomElements());

        return result;
    }


    public static void main(String[] args) {

        Gson gson = new Gson();
        PhoneDataExtractor extractor = new PhoneDataExtractor(0);
        String dataString = "[{\"mac\":\"f0:b4:29:76:96:1f\",\"rssi\":\"-68\",\"range\":\"129\",\"ts\":\"\",\"tmc\":\"\",\"tc\":\"\",\"ds\":\"\",\"essid0\":\"\",\"essid1\":\"\",\"essid2\":\"\",\"essid3\":\"\",\"essid4\":\"\",\"essid5\":\"\",\"essid6\":\"\",\"time\":1492595762000,\"text\":{\"bytes\":[49,50,57,98,52,58,50,57,58,55,54,58,57,54,58,49,102],\"length\":0},\"longWritable\":{\"value\":1492595762000}}]";


        List<PhoneJson> phoneData = gson.fromJson(
                dataString, new TypeToken<List<PhoneJson>>(){}.getType());

        PhoneJson prototype = phoneData.get(0);
        prototype.setRange("2");
        phoneData.clear();

        try {
            long bias = System.currentTimeMillis()/3600000*3600000-1000*60;
            for (int i=0; i<10; i++) {
                PhoneJson phoneJson = prototype.clone();
                phoneJson.setTime(i+bias);
                phoneData.add(phoneJson);
            }

            PhoneJson phone = prototype.clone();
            phone.setTime(20000+bias);
            phone.setRange("100");
            phoneData.add(phone);

            for (int i=0; i<10; i++) {
                PhoneJson phoneJson = prototype.clone();
                phoneJson.setTime(i+40000+bias);
                phoneData.add(phoneJson);
            }

            HourStatistic hourStatistic = extractor.extract(phoneData);

            System.out.println(Arrays.toString(hourStatistic.getCustomerFlowElements()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
