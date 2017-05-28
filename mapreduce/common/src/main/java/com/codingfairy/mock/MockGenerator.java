package com.codingfairy.mock;

import com.codingfairy.tool.DateFormatter;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.ProbeJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on 2017-05-21.
 * Generator
 */
public class MockGenerator {

    public static void main(String[] args) {

        try {
            long currentHour = System.currentTimeMillis()/(3600*1000);
            System.out.println(currentHour*3600000);
            String json = GsonTool.convertObjectToJson(generate( (currentHour-2)*3600000L,(currentHour-1L)*3600000L,10000));

            FileWriter fileWriter = new FileWriter(new File("mock.txt"));
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String[] mac = {
            "00:00:00:00:00:00",
            "11:11:11:11:11:11",
            "22:22:22:22:22:22",
            "33:33:33:33:33:33",
            "44:44:44:44:44:44",
            "55:55:55:55:55:55",
    };

    public static List<ProbeJson> generate(long start, long end, long rand) throws Exception{

        String string = "[{\"id\":\"1s12sz\",\"mmac\":\"5e:cf:7f:10:f3:77\",\"rate\":\"3\",\"wssid\":\"codingfairy\",\"wmac\":\"a8:57:4e:c0:d4:8c\",\"time\":\"Thu Jan 01 08:00:00 1970\",\"lat\":\"30.748093\",\"lon\":\"103.973083\",\"addr\":\"江苏省南京市汉口路22号\",\"data\":[{\"mac\":\"86:59:79:be:60:24\",\"rssi\":\"-77\",\"range\":\"293\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"c9:66:49:e3:81:69\",\"rssi\":\"-61\",\"range\":\"654\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"a7:35:cd:25:2c:e2\",\"rssi\":\"-82\",\"range\":\"115\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"4b:c4:17:3a:fd:d5\",\"rssi\":\"-58\",\"range\":\"534\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"d0:94:ca:e3:38:5b\",\"rssi\":\"-93\",\"range\":\"625\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"33:c6:13:dd:0d:bd\",\"rssi\":\"-97\",\"range\":\"425\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"30:1f:b4:36:c9:4c\",\"rssi\":\"-99\",\"range\":\"354\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"76:a5:53:b5:2e:a2\",\"rssi\":\"-96\",\"range\":\"398\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"ac:d4:f8:ba:fb:6c\",\"rssi\":\"-37\",\"range\":\"963\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"9e:8e:ab:8b:88:b9\",\"rssi\":\"-97\",\"range\":\"406\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"b1:69:cf:b5:e0:a2\",\"rssi\":\"-98\",\"range\":\"602\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"64:5f:88:da:f6:d3\",\"rssi\":\"-98\",\"range\":\"295\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"36:d4:aa:1a:c4:fd\",\"rssi\":\"-97\",\"range\":\"84\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"40:a5:87:ea:bf:60\",\"rssi\":\"-69\",\"range\":\"36\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"fc:31:4b:b6:a1:8b\",\"rssi\":\"-11\",\"range\":\"880\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"32:18:b0:d8:e8:ab\",\"rssi\":\"-93\",\"range\":\"133\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"32:ce:b2:1d:e3:ad\",\"rssi\":\"-92\",\"range\":\"624\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"58:94:07:27:68:da\",\"rssi\":\"-78\",\"range\":\"541\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"Y\",\"ds\":\"Y\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}},{\"mac\":\"41:46:c0:2d:76:b0\",\"rssi\":\"-93\",\"range\":\"304\",\"ts\":\"codingfairy\",\"tmc\":\"00:01:02:03:04:05\",\"tc\":\"N\",\"ds\":\"N\",\"essid0\":\"七天连锁_wifi\",\"essid1\":\"工商银行\",\"essid2\":\"东方明珠\",\"essid3\":\"home\",\"essid4\":\"abcd\",\"essid5\":\"xiong\",\"essid6\":\"XX会馆\",\"time\":0,\"text\":{\"bytes\":[],\"length\":0},\"longWritable\":{\"value\":0}}]}]";
        List<ProbeJson> probeJsonList = new Gson().fromJson(
                string, new TypeToken<List<ProbeJson>>(){}.getType());


        ProbeJson probeJsonPrototype = probeJsonList.get(0).clone();
        PhoneJson phoneJsonPrototype = probeJsonList.get(0).getData().get(0);


        probeJsonList.clear();



        for (long time = start; time < end; time+=Math.random()*rand) {

            PhoneJson phoneJson = phoneJsonPrototype.clone();
            phoneJson.setMac(mac[(int)Math.random()*mac.length]);
            ArrayList<PhoneJson> dataList = new ArrayList<PhoneJson>();
            dataList.add(phoneJson);

            ProbeJson probeJson = probeJsonPrototype.clone();
            probeJson.setTime(DateFormatter.toString(time));
            probeJson.setData(dataList);

            probeJsonList.add(probeJson);
        }

        return probeJsonList;
    }
}
