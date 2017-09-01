package com.codingfairy.mapreduce.save;

import com.codingfairy.config.FileConfig;
import com.codingfairy.tool.HDFSTool;
import com.codingfairy.tool.Logger;

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
    private long __executeHourTime;

    private Reader reader ;

    public Saver(long __startTime, long __executeHourTime) {
        this.__startTime = __startTime;
        this.__executeHourTime = __executeHourTime;
        this.reader = new Reader();
    }

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
            Logger.println("directory: "+directories.toString());
            for (String dir : directories) {

                String subDirectory = __directory+"/"+dir;
                Logger.println("sub direct: "+subDirectory);
                long time = _getDirTime(dir);

                if (time>=0 && time>=__startTime) {
                    List<String> statisticFiles = HDFSTool.getDirectoryFromHdfs(subDirectory);
                    Logger.println("    files: "+statisticFiles);
                    for (String file : statisticFiles) {
                        DataInputStream inputStream = HDFSTool.readFromHdfs(__directory+"/"+dir+"/"+file);
                        reader.readStream(file, inputStream);
                    }
                }
            }

            // 计算活跃度
            reader.summary(__executeHourTime, null);
            // 将读取出来的结果存入数据库
            new Storer().store(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
