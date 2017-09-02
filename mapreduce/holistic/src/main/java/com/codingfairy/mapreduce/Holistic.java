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

        long time;
        long loop;
        try {
            time = Long.parseLong(args[0]);
            loop = Long.parseLong(args[1]);
        } catch (Exception e) {
            time = 0;
            loop = -1;
        }

        /**
         * 负数表示前多少个小时的
         */
        if (time<0) {
            time = System.currentTimeMillis()/HOUR*HOUR + time*HOUR;
        }

        while (true) {


            Logger.println("[Holistic]: start time: "+time);
            try {
                long lastTime = time;
                long currentTimeMillis = System.currentTimeMillis();
                time = currentTimeMillis/HOUR*HOUR;

                if (time>lastTime+HOUR) {

                    if (loop==0) {
                        break;
                    } else if (loop>0) {
                        loop--;
                        //loop util loop==0
                    } else if (loop<0) {
                        //loop forever
                    }

                    new Task(lastTime).execute();
                    Saver saver = new Saver(lastTime, time, "1s12sz");
                    Logger.println("[Holistic]: statistic done, start save data in database");
                    saver.run();
                    Logger.println("[Holistic]: save done.");

                } else {
                    Logger.println("wait, \n    lastTime:"+lastTime
                            +"\n    currentTime"+time);
                    try {
                        Thread.sleep(1000*60);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //避免lastTime的值变化
                    time = lastTime;
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
