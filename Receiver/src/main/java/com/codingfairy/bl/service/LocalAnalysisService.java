package com.codingfairy.bl.service;

import com.codingfairy.web.json.ProbeJson;

import java.util.List;

/**
 * @author darxyan
 * @version 2017-05-09 20:20:31
 */
public interface LocalAnalysisService {


    /**
     * Upload string to file system
     * @see LocalAnalysisService#uploadFiles(ProbeJson)
     * @param probJson string
     * @return ?
     */
    Object uploadFiles(String probJson);
    /**
     * 将数据提交到远端服务器
     * @param probeJson 字符串对象
     */
    Object uploadFiles(ProbeJson probeJson);

    /**
     * 将列表数据提交到服务器
     * @param probeJsons list of {@link ProbeJson}
     * @return ?
     */
    Object uploadFiles(List<ProbeJson> probeJsons);

    /**
     * 向远端服务器获取指定类型的分析数据
     * @param dataType 字符串对象
     */
    Object getCalculatedAnalysis(Object dataType);

    /**
     * 要求远端服务器实时分析数据，阻塞返回
     * @param dataType data type
     */
    Object getRealTimeAnalysis(Object dataType) ;
}
