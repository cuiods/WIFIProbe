package com.codingfairy.mock;

import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.ProbeJson;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cuihao on 2017-05-20.
 * Mock false json data
 */
public class MockData {

    private List<String> oldCustomer = new LinkedList<String>();
    private Map<String,Integer> rangeMap = new HashMap<String, Integer>();

    public MockData () {
        //生成100个老顾客的mac地址
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 2; k++) {
                    char c;
                    if (Math.random()>0.625) {
                        c = (char) generateRandom(random,97,102);
                    } else {
                        c = (char) generateRandom(random,48,57);
                    }
                    builder.append(c);
                }
                if (j!=5) builder.append(":");
            }
            oldCustomer.add(builder.toString());
        }
    }

    public List<ProbeJson> mockProbeJsonList(long startTime, long endTime, long interval) {
        assert startTime<endTime;
        List<ProbeJson> probeJsons = new LinkedList<ProbeJson>();
        Random random = new Random();
        for (long time = startTime; time <= endTime; time += generateRandom(random, 100, (int)interval) ) {
            probeJsons.add(mockProbeJson(time));
        }
        return probeJsons;
    }

    private ProbeJson mockProbeJson(long time) {
        ProbeJson probeJson = new ProbeJson();
        probeJson.setId("1s12sz");
        probeJson.setMmac("5e:cf:7f:10:f3:77");
        probeJson.setRate("3");
        probeJson.setWssid("codingfairy");
        probeJson.setWmac("a8:57:4e:c0:d4:8c");
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        probeJson.setTime(dateFormat.format(date));
        probeJson.setLat("30.748093");
        probeJson.setLon("103.973083");
        probeJson.setAddr("江苏省南京市汉口路22号");
        probeJson.setData(mockPhoneJson());
        return probeJson;
    }

    private List<PhoneJson> mockPhoneJson() {
        Map<String,PhoneJson> phoneJsons = new HashMap<String, PhoneJson>();
        Map<String,Integer> currentRange = new HashMap<String, Integer>();
        Random sizeRandom = new Random(System.currentTimeMillis());
        //add new customer
        for (int i = 0; i < generateRandom(sizeRandom,20,50); i++) {
            PhoneJson phoneJson = new PhoneJson();
            StringBuilder builder = new StringBuilder();
            if (Math.random()>0.5) {
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 2; k++) {
                        char c;
                        if (Math.random()>0.625) {
                            c = (char) generateRandom(sizeRandom,97,102);
                        } else {
                            c = (char) generateRandom(sizeRandom,48,57);
                        }
                        builder.append(c);
                    }
                    if (j!=5) builder.append(":");
                }
            } else {
                builder = new StringBuilder(oldCustomer.get(generateRandom(sizeRandom,0,oldCustomer.size()-1)));
            }
            phoneJson.setMac(builder.toString());
            phoneJson.setRange(generateRandom(sizeRandom,1,1000)+"");
            currentRange.put(phoneJson.getMac(),Integer.valueOf(phoneJson.getRange()));
            phoneJson.setRssi((-(100-generateRandom(sizeRandom,1,Integer.valueOf(phoneJson.getRange())%98+2)))+"");
            phoneJson = packagePhoneJson(phoneJson);
            phoneJsons.put(phoneJson.getMac(),phoneJson);
        }
        //use last mac
        for (String mac: rangeMap.keySet()) {
            if (Math.random()>=0.71) continue;
            int range = rangeMap.get(mac);
            if (range<1) continue;
            PhoneJson phoneJson = new PhoneJson();
            phoneJson.setMac(mac);
            int random = 0;
            if (Math.random()>0.8) {
                random+=generateRandom(sizeRandom,0,1000-range>100?generateRandom(sizeRandom,0,100):1000-range);
            } else {
                random-=generateRandom(sizeRandom,0,range-1>100?generateRandom(sizeRandom,0,100):(range-1>0?range-1:1));
            }
            phoneJson.setRange(random+range+"");
            phoneJson.setRssi((-(100-generateRandom(sizeRandom,1,Integer.valueOf(phoneJson.getRange())%98+2)))+"");
            phoneJson = packagePhoneJson(phoneJson);
            currentRange.put(mac,Integer.valueOf(phoneJson.getRange()));
        }
        rangeMap = new HashMap<String, Integer>();
        for (String mac: currentRange.keySet()) {
            if (Math.random()>=0.71) {
                rangeMap.put(mac,currentRange.get(mac));
            }
        }
        return new ArrayList<PhoneJson>(phoneJsons.values());
    }

    private int generateRandom(Random random, int min, int max) {
        if (max<1) max = 1;
        return  random.nextInt(max)%(max-min+1) + min;
    }

    private PhoneJson packagePhoneJson(PhoneJson phoneJson) {
        phoneJson.setTs("codingfairy");
        phoneJson.setTmc("00:01:02:03:04:05");
        phoneJson.setTc(Math.random()>0.5?"N":"Y");
        phoneJson.setDs(Math.random()>0.5?"N":"Y");
        phoneJson.setEssid0("七天连锁_wifi");
        phoneJson.setEssid1("工商银行");
        phoneJson.setEssid2("东方明珠");
        phoneJson.setEssid3("home");
        phoneJson.setEssid4("abcd");
        phoneJson.setEssid5("xiong");
        phoneJson.setEssid6("XX会馆");
        return phoneJson;
    }

}
