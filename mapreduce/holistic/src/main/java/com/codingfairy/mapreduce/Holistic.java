package com.codingfairy.mapreduce;

import com.codingfairy.config.NodeConfig;
import com.codingfairy.tool.Logger;


/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME = "START_TIME";


    private static void start(String[] args){
        long time;
        try {
            time = Long.parseLong(args[0]);
        }catch (Exception e) {
            time = System.currentTimeMillis() - NodeConfig.MAX_WIFI_DATA_INTERVAL;
        }

        while (true) {
            try {
                long lastTime = time;
                Logger.println("loop: ");
                Logger.println("time: "+time);
                time = System.currentTimeMillis() - NodeConfig.MAX_WIFI_DATA_INTERVAL;
                new Task(lastTime).execute();
            }catch (Exception e) {
                e.printStackTrace();
            }
            //just execute one loop when debug
            break;
        }
    }

    public static void main(String[] args) throws Exception{
        start(args);
    }
}
