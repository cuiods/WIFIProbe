package com.codingfairy.mapreduce.test;

import com.codingfairy.mapreduce.config.IsContinuous;
import com.codingfairy.mapreduce.logic.PhoneDataExtractor;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by darxan on 2017/8/23.
 */
public class PhoneDataExtractorTest {

    private List<PhoneJson> jsonList;

    @Before
    public void before() {
        Gson gson = new Gson();
        String dataString = "[{\"mac\":\"f0:b4:29:76:96:1f\",\"rssi\":\"-68\",\"range\":\"129\",\"ts\":\"\",\"tmc\":\"\",\"tc\":\"\",\"ds\":\"\",\"essid0\":\"\",\"essid1\":\"\",\"essid2\":\"\",\"essid3\":\"\",\"essid4\":\"\",\"essid5\":\"\",\"essid6\":\"\",\"time\":1492595762000,\"text\":{\"bytes\":[49,50,57,98,52,58,50,57,58,55,54,58,57,54,58,49,102],\"length\":0},\"longWritable\":{\"value\":1492595762000}}]";
        jsonList = gson.fromJson(
                dataString, new TypeToken<List<PhoneJson>>(){}.getType());
    }

    @Test
    public void test() {

        PhoneDataExtractor extractor = new PhoneDataExtractor(0);

        List<PhoneJson> phoneData = new ArrayList<PhoneJson>(jsonList);
        PhoneJson prototype = phoneData.get(0);
        prototype.setRange("2");
        phoneData.clear();

        try {
            long currentHourStartTime = (System.currentTimeMillis()/3600000-1)*3600000;

            for (int i=0; i<10; i++) {
                PhoneJson phoneJson = prototype.clone();
                phoneJson.setTime(IsContinuous.INTERVAL_MAX*2+i+currentHourStartTime);
                phoneData.add(phoneJson);
            }

            PhoneJson phone = prototype.clone();
            phone.setTime(IsContinuous.INTERVAL_MAX*5+currentHourStartTime);
            phone.setRange("100");
            phoneData.add(phone);

            for (int i=0; i<10; i++) {
                PhoneJson phoneJson = prototype.clone();
                phoneJson.setTime(i+IsContinuous.INTERVAL_MAX*8+currentHourStartTime);
                phoneData.add(phoneJson);
            }

            System.out.println("==================================");
            for (PhoneJson item : phoneData) {
                System.out.println(item.getTime());
            }
            System.out.println("==================================");

            HourStatistic hourStatistic = extractor.extract(phoneData);

            System.out.println(Arrays.toString(hourStatistic.getCustomerFlowElements()));
            System.out.println(Arrays.toString(hourStatistic.getNewOldCustomElements()));
            System.out.println(hourStatistic.getCycles().toString());
            System.out.println(hourStatistic.getInStoreHours().toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
