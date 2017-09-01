package com.codingfairy.mapreduce.save;

import com.codingfairy.config.NodeConfig;
import com.codingfairy.mock.GsonTool;
import com.codingfairy.tool.HttpRequestUtil;
import com.codingfairy.vo.analysis.ChartData;

import java.util.List;

/**
 * Created by darxan on 2017/8/27.
 */
public class Storer {



    private String __ip ="http://"+ NodeConfig.RECEIVER+"/api/v1/result";

    private String __ipPutInStore = __ip+"/storeHours";
    private String __ipPutVisitCycle = __ip+"/visitCircle";
    private String __ipPutCustomFlow = __ip+"/flow";
    private String __ipPutActiveness = __ip+"/activeness";
    private String __ipPutNewOld = __ip+"/newOld";
    
    public void store(Reader reader) {

        if (!isEmpty(reader.customerFlows) ) {
            HttpRequestUtil.sendJsonPost(__ipPutCustomFlow,
                    GsonTool.convertObjectToJson(reader.customerFlows));
        }
        if (!isEmpty(reader.newOldCustoms))
            HttpRequestUtil.sendJsonPost(__ipPutNewOld,
                    GsonTool.convertObjectToJson(reader.newOldCustoms));
        if (!isEmpty(reader.activeness))
            HttpRequestUtil.sendJsonPost(__ipPutActiveness,
                    GsonTool.convertObjectToJson(reader.activeness));
        if (!isEmpty(reader.inStoreHours))
            HttpRequestUtil.sendJsonPost(__ipPutInStore,
                    GsonTool.convertObjectToJson(reader.inStoreHours));
        if (!isEmpty(reader.visitingCycles))
            HttpRequestUtil.sendJsonPost(__ipPutVisitCycle,
                    GsonTool.convertObjectToJson(reader.visitingCycles));
    }

    private boolean isEmpty(List list) {
        return list==null||list.isEmpty();
    }

    private boolean isEmpty(ChartData chartData) {
        return chartData==null
                ||chartData.getStatistic()==null
                ||chartData.getStatistic().isEmpty();
    }

}
