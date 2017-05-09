package com.codingfairy.analysis.impl;

import com.codingfairy.analysis.ServerAnalysisService;
import com.codingfairy.analysis.config.NodeConfig;
import com.codingfairy.web.json.ProbeJson;
import com.google.gson.Gson;

import java.rmi.Naming;

/**
 * Created by darxan on 2017/5/8.
 */
public class RMIClient {

    /**
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T  getService(Class<T> cls) {
        if (cls==null) {
            return null;
        }
        for (int i=0; i<1; i++) {
            try {
                return  (T) Naming.lookup(NodeConfig.ANALYSIS_SERVER+"/"+cls.getSimpleName());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
