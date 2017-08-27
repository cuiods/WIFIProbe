package com.codingfairy.mapreduce.save;

import com.codingfairy.config.FileConfig;
import com.codingfairy.tool.HDFSTool;
import java.io.DataInputStream;
import java.util.List;

/**
 * Created by darxan on 2017/8/19.
 */
public class Saver implements Runnable {

    /**
     * 统计数据的目录
     */
    private final String __directory = FileConfig.statistic;

    /**
     * 只更新该时间后的数据
     */
    private long __startTime;

    private Reader reader = new Reader();

    /**
     * 根据目录名字获取对应的时刻
     * @param dir
     * @return
     */
    private long _getDirTime(String dir) {
        long time;
        try {
            time = Long.parseLong(dir);
        } catch (Exception e) {
            time = -1;
        }
        return time;
    }

    public void run()  {

        try {
            List<String> directories = HDFSTool.getDirectoryFromHdfs(__directory);
            for (String dir : directories) {

                long time = _getDirTime(dir);

                if (time>=0 && time>=__startTime) {
                    List<String> statisticFiles = HDFSTool.getDirectoryFromHdfs(__directory+"/"+dir);
                    for (String file : statisticFiles) {
                        DataInputStream inputStream = HDFSTool.readFromHdfs(__directory+"/"+dir+"/"+file);
                        reader.readStream(file, inputStream);
                    }
                }
            }

            // 计算活跃度
            reader.summary();
            // 将读取出来的结果存入数据库
            new Storer().store(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
