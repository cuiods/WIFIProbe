package com.codingfairy.analysis.impl;

import com.codingfairy.analysis.AnalysisService;
import com.codingfairy.analysis.config.NodeConfig;

import java.rmi.Naming;

/**
 * Created by darxan on 2017/5/8.
 */
public class AnalysisFactory {

    public static <T> T  getService(Class<T> cls) {

        for (int i=0; i<10; i++) {
            try {
                return  (T) Naming.lookup(NodeConfig.ANALYSIS_SERVER+"/"+cls.getSimpleName());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
