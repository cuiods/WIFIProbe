package com.codingfairy;

import com.codingfairy.config.FileConfig;
import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.mock.GsonTool;
import com.codingfairy.tool.HDFSTool;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
                        _parseFile(__directory+"/"+dir+"/"+file);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _parseFile(String file) throws IOException {
        DataInputStream inputStream = HDFSTool.readFromHdfs(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if (file.startsWith(MapKeyConfig.CUSTOMER_FLOW_KEY)) {
            for (String line; (line=reader.readLine())!=null; ) {
                String[] temp =line.split(" ", 2);
                if (temp.length==2) {
                    CustomerFlowElement customerFlowElement = new Gson().fromJson(
                            temp[1], CustomerFlowElement.class);
                }
            }

        } else if (file.startsWith(MapKeyConfig.CYCLE)) {
            for (String line; (line=reader.readLine())!=null; ) {
                String[] temp =line.split(" ", 2);
                if (temp.length==2) {

                }
            }
        } else if (file.startsWith(MapKeyConfig.IN_STORE_HOUR)) {
            for (String line; (line=reader.readLine())!=null; ) {
                String[] temp =line.split(" ", 2);
                if (temp.length==2) {

                }
            }
        } else if (file.startsWith(MapKeyConfig.NEW_OLD_CUSTOMER)) {
            for (String line; (line=reader.readLine())!=null; ) {
                String[] temp =line.split(" ", 2);
                if (temp.length==2) {
                    NewOldCustomElement newOldCustomElement = new Gson().fromJson(
                            temp[1], NewOldCustomElement.class);
                }
            }
        }
        reader.close();
    }
}
