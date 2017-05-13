package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.LocalAnalysisService;
import com.codingfairy.web.json.ProbeJson;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author darxyan
 * @version 2017-05-09 19:29:20
 */
public class AnalysisServiceImpl implements LocalAnalysisService {

    private Gson gson ;
    public AnalysisServiceImpl() {
        gson = new Gson();
    }

    public Object uploadFiles(String probJson) {
        return null;
    }


    @Override
    public Object uploadFiles(ProbeJson probeJson) {
        return null;
    }

    /**
     * 将列表数据提交到服务器
     * <em>提交完成后需要清空列表</em>
     *
     * @param probeJsons list of {@link ProbeJson}
     * @return ?
     */
    @Override
    public Object uploadFiles(List<ProbeJson> probeJsons) {
        return null;
    }

    @Override
    public Object getCalculatedAnalysis(Object dataType) {
        return null;
    }

    @Override
    public Object getRealTimeAnalysis(Object dataType) {
        return null;
    }


}
