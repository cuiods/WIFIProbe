package com.codingfairy.analysis;

import java.io.InputStream;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by darxan on 2017/5/8.
 */
public interface AnalysisService extends Remote {
    /**
     * 启动一个线程，使用probeJsons中的数据上传至HDFS数据
     * @param probeJson
     */
    void uploadFiles(ProbeJson probeJson) throws RemoteException;

    /**
     * 获取指定类型的分析数据
     * @param dataType
     */
    Serializable getCalculatedAnalysis(Serializable dataType) throws RemoteException;

    /**
     * 实时分析数据，阻塞返回
     * @param dataType
     */
    Serializable getRealTimeAnalysis(Serializable dataType) throws RemoteException;

}
