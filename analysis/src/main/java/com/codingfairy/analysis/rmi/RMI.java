package com.codingfairy.analysis.rmi;

import com.codingfairy.analysis.ServerAnalysisService;
import com.codingfairy.analysis.impl.DefaultAnalysisService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by darxan on 2017/5/8.
 */
public class RMI {

    public void start() {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://115.159.214.227:1099/"+ ServerAnalysisService.class.getSimpleName(),
                    new DefaultAnalysisService());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
