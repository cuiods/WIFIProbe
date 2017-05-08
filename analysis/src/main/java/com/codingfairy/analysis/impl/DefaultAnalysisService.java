package com.codingfairy.analysis.impl;

import com.codingfairy.analysis.AnalysisService;
import com.codingfairy.web.json.ProbeJson;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by darxan on 2017/5/8.
 */
public class DefaultAnalysisService extends UnicastRemoteObject implements AnalysisService {

    public DefaultAnalysisService() throws RemoteException
    {
    }


    public void uploadFiles(ProbeJson probeJson) throws RemoteException {
        System.out.println(probeJson);
    }

    public Serializable getCalculatedAnalysis(Serializable dataType) throws RemoteException {
        return null;
    }

    public Serializable getRealTimeAnalysis(Serializable dataType) throws RemoteException {
        return null;
    }
}
