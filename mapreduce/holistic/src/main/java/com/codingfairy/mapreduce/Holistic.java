package com.codingfairy.mapreduce;

import com.codingfairy.config.NodeConfig;
import com.codingfairy.mapreduce.save.Saver;
import com.codingfairy.tool.Logger;


/**
 * Created by darxan on 2017/5/15.
 */
public class Holistic {


    public static String START_TIME = "START_TIME";
    public static String EXECUTE_TIME = "EXECUTE_TIME";


    private static void start(String[] args){

        long time;
        try {
            time = Long.parseLong(args[0]);
        }catch (Exception e) {
            time = 0;
        }

        Saver saver = new Saver(time, System.currentTimeMillis());

        while (true) {

            try {
                long lastTime = time;
                Logger.println("[Holistic]: loop");
                Logger.println("[Holistic]: start time: "+time);
                time = System.currentTimeMillis() - NodeConfig.MAX_WIFI_DATA_INTERVAL;
                new Task(lastTime).execute();
            }catch (Exception e) {
                e.printStackTrace();
            }

            Logger.println("[Holistic]: statistic done, start save data in database");
            saver.run();
            Logger.println("[Holistic]: save done.");
            //just execute one loop when debug
            break;
        }
    }

    public static void main(String[] args) throws Exception{
        start(args);
    }
}
