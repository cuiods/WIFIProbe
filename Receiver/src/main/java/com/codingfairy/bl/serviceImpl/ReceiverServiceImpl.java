package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.LocalAnalysisService;
import com.codingfairy.bl.cache.ConcurrentDataList;
import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Receiver service impl
 */
@Service
public class ReceiverServiceImpl implements ReceiverService {

    @Resource
    private LocalAnalysisService analysisService;

    private ConcurrentDataList concurrentDataList = ConcurrentDataList.instance();

    /**
     * Save json data to cache
     *
     * @param probeJson json data
     */
    @Override
    public void receive(ProbeJson probeJson) {
        concurrentDataList.addProbeJson(probeJson);
    }

    /**
     * Commit json file to data storage system
     */
    @Override
    @Scheduled(cron = "0 0/20 7-23 * * ?")
    public void commit() {
        List<ProbeJson> probeJsons = concurrentDataList.getAll();
        analysisService.uploadFiles(probeJsons);
    }

    /**
     * Get real time statistic info
     *
     * @return {@link RealTimeJson}
     */
    @Override
    public RealTimeJson statLatest() {
        return null;
    }
}
