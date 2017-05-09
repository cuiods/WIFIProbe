package com.codingfairy.analysis;

import com.codingfairy.web.json.ProbeJson;

import java.io.InputStream;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by darxan on 2017/5/8.
 */
public interface ServerAnalysisService extends Remote{
    /**
     * 启动一个线程，使用probeJsons中的数据上传至HDFS数据
     * @param probeJson 字符串对象
     */
    Serializable uploadFiles(Serializable probeJson) throws RemoteException;

    /**
     * 获取指定类型的分析数据
     * @param dataType 字符串对象
     */
    Serializable getCalculatedAnalysis(Serializable dataType) throws RemoteException;

    /**
     * 实时分析数据，阻塞返回
     * @param dataType
     */
    Serializable getRealTimeAnalysis(Serializable dataType) throws RemoteException;
}
