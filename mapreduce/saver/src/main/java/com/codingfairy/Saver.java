package com.codingfairy;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.mock.GsonTool;
import com.codingfairy.tool.HDFSTool;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by darxan on 2017/8/19.
 */
public class Saver implements Runnable {

    /**
     * 统计数据的目录
     */
    private String __directory;

    public void run()  {

        try {
            List<String> files = HDFSTool.getDirectoryFromHdfs(__directory);
            for (String file : files) {

                if (file.startsWith(MapKeyConfig.CUSTOMER_FLOW_KEY)) {
                    DataInputStream inputStream = HDFSTool.readFromHdfs(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    for (String line; (line=reader.readLine())!=null; ) {
                        CustomerFlowElement customerFlowElement = new Gson().fromJson(
                                line, CustomerFlowElement.class);
                    }

                } else if (file.startsWith(MapKeyConfig.CYCLE)) {

                } else if (file.startsWith(MapKeyConfig.IN_STORE_HOUR)) {

                } else if (file.startsWith(MapKeyConfig.NEW_OLD_CUSTOMER)) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
