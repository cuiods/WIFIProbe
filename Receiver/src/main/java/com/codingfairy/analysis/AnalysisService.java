package com.codingfairy.analysis;

import java.io.InputStream;

/**
 * Created by darxan on 2017/5/8.
 */
public interface AnalysisService {
    /**
     * 启动一个线程，使用stream中的数据上传至HDFS数据
     * @param stream
     */
    void uploadFiles(InputStream stream);

    /**
     * 获取指定类型的分析数据
     * @param dataType
     */
    void getCalculatedAnalysis(Object dataType);

    /**
     * 实时分析数据，阻塞返回
     * @param dataType
     */
    void getRealTimeAnalysis(Object dataType);
}
