package com.codingfairy.analysis;

import com.codingfairy.web.json.ProbeJson;

/**
 * Created by darxan on 2017/5/9.
 */
public interface LocalAnalysisService {
    /**
     * 将数据提交到远端服务器
     * @param probeJson 字符串对象
     */
    Object uploadFiles(ProbeJson probeJson) ;

    /**
     * 向远端服务器获取指定类型的分析数据
     * @param dataType 字符串对象
     */
    Object getCalculatedAnalysis(Object dataType);

    /**
     * 要求远端服务器实时分析数据，阻塞返回
     * @param dataType
     */
    Object getRealTimeAnalysis(Object dataType) ;
}
