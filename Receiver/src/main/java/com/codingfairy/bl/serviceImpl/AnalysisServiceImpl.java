package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.LocalAnalysisService;
import com.codingfairy.bl.config.NodeConfig;
import com.codingfairy.web.json.ProbeJson;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;

/**
 * @author darxyan
 * @version 2017-05-09 19:29:20
 */
public class AnalysisServiceImpl implements LocalAnalysisService {

    private Gson gson ;
    private Jedis jedis;
    public AnalysisServiceImpl() {
        gson = new Gson();
        jedis = new Jedis(NodeConfig.REDIS_SERVER, NodeConfig.REDIS_PORT);
        jedis.auth(NodeConfig.REDIS_AUTH);
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
