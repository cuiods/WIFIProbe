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


    private static final long HOUR = 1000L*60L*60L;

    private static void start(String[] args){

        long executeTime;
        long loop;
        try {
            executeTime = Long.parseLong(args[0]);
            loop = Long.parseLong(args[1]);
            executeTime++;
        } catch (Exception e) {
            executeTime = 0;
            loop = -1;
        }

        /**
         * 负数表示前多少个小时的
         */
        if (executeTime<0) {
            executeTime = System.currentTimeMillis()/HOUR*HOUR + executeTime*HOUR;
        }

        while (true) {


            Logger.println("[Holistic]: start time: "+executeTime);
            try {
                long startTime = executeTime;
                long currentTimeMillis = System.currentTimeMillis();
                executeTime = currentTimeMillis/HOUR*HOUR;

                if (executeTime>=startTime+HOUR) {

                    if (loop==0) {
                        break;
                    } else if (loop>0) {
                        loop--;
                        //loop util loop==0
                    } else if (loop<0) {
                        //loop forever
                    }

                    new Task(startTime, executeTime).execute();
                    Saver saver = new Saver(startTime, executeTime, "1s12sz");
                    Logger.println("[Holistic]: statistic done, start save data in database");
                    saver.run();
                    Logger.println("[Holistic]: save done.");

                } else {
                    Logger.println("wait, \n    lastTime:"+startTime
                            +"\n    currentTime"+executeTime);
                    try {
                        Thread.sleep(1000*60);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //避免lastTime的值变化
                    executeTime = startTime;
                }

            }catch (Exception e) {
                e.printStackTrace();
            }

            //just execute one loop when debug
//            break;
        }
    }

    public static void main(String[] args) throws Exception{
        start(args);
    }
}
