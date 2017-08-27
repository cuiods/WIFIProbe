package com.codingfairy.mapreduce.save;

import com.codingfairy.mock.GsonTool;
import com.codingfairy.tool.HttpRequestUtil;

/**
 * Created by darxan on 2017/8/27.
 */
public class Storer {



    private String __ip ="http://127.0.0.1/api/v1/result";

    private String __ipPutInStore = __ip+"/storeHours";
    private String __ipPutVisitCycle = __ip+"/visitCircle";
    private String __ipPutCustomFlow = __ip+"/flow";
    private String __ipPutActiveness = __ip+"/activeness";
    private String __ipPutNewOld = __ip+"/newOld";
    
    public void store(Reader reader) {

        HttpRequestUtil.sendJsonPost(__ipPutCustomFlow,
                GsonTool.convertObjectToJson(reader.customerFlows));
        HttpRequestUtil.sendJsonPost(__ipPutNewOld, 
                GsonTool.convertObjectToJson(reader.newOldCustoms));
        HttpRequestUtil.sendJsonPost(__ipPutActiveness, 
                GsonTool.convertObjectToJson(reader.activeness));
        HttpRequestUtil.sendJsonPost(__ipPutInStore, 
                GsonTool.convertObjectToJson(reader.inStoreHours));
        HttpRequestUtil.sendJsonPost(__ipPutVisitCycle, 
                GsonTool.convertObjectToJson(reader.visitingCycles));
    }

}
