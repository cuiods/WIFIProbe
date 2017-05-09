package com.codingfairy.analysis.impl;

import com.codingfairy.analysis.ServerAnalysisService;
import com.codingfairy.web.json.ProbeJson;
import com.google.gson.Gson;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by darxan on 2017/5/8.
 */
public class DefaultAnalysisService extends UnicastRemoteObject implements ServerAnalysisService {

    public DefaultAnalysisService() throws RemoteException
    {
        gson = new Gson();
    }



    private Gson gson;

    private static long firstTime = 0;
    public Serializable uploadFiles(Serializable probeJson) throws RemoteException {
        if (firstTime==0) {
            firstTime = System.currentTimeMillis();
        }
        ProbeJson probeJsonObj = gson.fromJson(probeJson.toString(), ProbeJson.class);
        System.out.println(probeJsonObj);

        System.out.println(System.currentTimeMillis()-firstTime);
        return null;
    }

    public Serializable getCalculatedAnalysis(Serializable dataType) throws RemoteException {
        return null;
    }

    public Serializable getRealTimeAnalysis(Serializable dataType) throws RemoteException {
        return null;
    }
}
