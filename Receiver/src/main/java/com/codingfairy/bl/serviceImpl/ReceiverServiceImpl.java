package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.config.ReceiverConfig;
import com.codingfairy.bl.service.LocalAnalysisService;
import com.codingfairy.bl.cache.ConcurrentDataList;
import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.bl.tool.GsonTool;
import com.codingfairy.bl.tool.HDFSTool;
import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

/**
 * Receiver service impl
 */
@Service
public class ReceiverServiceImpl implements ReceiverService {

    @Resource
    private ReceiverConfig receiverConfig;

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
        new Thread( () -> {
            if (concurrentDataList.getSize()>0) {
                int sleep = (int) (Math.random()*60000);
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<ProbeJson> probeJsons = concurrentDataList.getAll();
                InputStream inputStream =
                        new ByteArrayInputStream(GsonTool.convertObjectToJson(probeJsons).getBytes());
                try {
                    Calendar c = Calendar.getInstance();
                    HDFSTool.uploadFiles(inputStream, String.valueOf(c.get(Calendar.YEAR)) + "-"
                            + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + "/"
                            + c.get(Calendar.MILLISECOND) + "-" + receiverConfig.getName()+".json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Get real time statistic info
     *
     * @return {@link RealTimeJson}
     */
    @Override
    public RealTimeJson statLatest() {
        RealTimeJson realTimeJson = new RealTimeJson();
        ProbeJson latestProbe = concurrentDataList.getLatest();
        realTimeJson.setOsName(System.getProperty("os.name"));
        realTimeJson.setOsArch(System.getProperty("os.arch"));
        realTimeJson.setOsVersion(System.getProperty("os.version"));
        realTimeJson.setBufferSize(concurrentDataList.getSize());
        realTimeJson.setServerName(receiverConfig.getName());
        if (latestProbe!=null) {
            realTimeJson.setConnectNum(latestProbe.getData().size());
            realTimeJson.setLatestCommit(latestProbe.getTime());
        }
        return realTimeJson;
    }
}
