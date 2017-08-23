package com.codingfairy.mapreduce.test;

import com.codingfairy.vo.ProbeJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by darxan on 2017/8/19.
 */
public class ParseProbJson {

    public static void main(String[] args) {
        String string = "[{\"id\": \"007db104\",\"data\":[{\"mac\":\"f0:b4:29:76:96:1f\",\"rssi\":\"-68\",\"range\":\"129\",\"ts\":\"\",\"tmc\":\"\",\"tc\":\"\",\"ds\":\"\",\"essid0\":\"\",\"essid1\":\"\",\"essid2\":\"\",\"essid3\":\"\",\"essid4\":\"\",\"essid5\":\"\",\"essid6\":\"\",\"time\":1492595762000,\"text\":{\"bytes\":[49,50,57,98,52,58,50,57,58,55,54,58,57,54,58,49,102],\"length\":0},\"longWritable\":{\"value\":1492595762000}}],\"mmac\": \"20:f4:1b:7d:b1:04\",\"rate\": \"3\",\"time\": \"Wed Apr 19 17:56:02 2017\",\"lat\": \"\",\"lon\": \"\"}]";
        List<ProbeJson> probeJsons = new Gson().fromJson(
                string, new TypeToken<List<ProbeJson>>(){}.getType());
        System.out.println(probeJsons);
    }

}
