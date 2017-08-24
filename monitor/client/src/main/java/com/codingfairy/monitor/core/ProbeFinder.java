package com.codingfairy.monitor.core;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on 2017-08-24.
 * Find all probes in the local net
 */
public class ProbeFinder {

    public static List<String> getConnectedProbes() {
        String localIp = HttpTools.getLocalIp();
        localIp = "http://"+localIp;
        List<String> resultList = new ArrayList<>(10);
        int dotIndex = localIp.lastIndexOf(".");
        localIp = localIp.substring(0,dotIndex+1);
        for (int i = 2; i < 255; i++) {
            String testIp = localIp+i;
            if (!testIp.equals(localIp)) {
                URLConnection connection = HttpTools.sendGet(testIp);
                if (connection!=null) {
                    resultList.add(testIp);
                }
            }
        }
        return resultList;
    }

}
