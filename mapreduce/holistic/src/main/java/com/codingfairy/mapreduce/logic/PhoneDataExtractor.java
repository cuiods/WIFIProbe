package com.codingfairy.mapreduce.logic;

import com.codingfairy.mapreduce.config.IsContinuous;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.HourStatistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

/**
 * Created by darxan on 17-5-17.
 */
public class PhoneDataExtractor {

    /**
     * 传入参数：时间
     * > current 错误，按照异常处理，统计前2个小时的数据
     * < current >0 统计time到当前的数据
     * < 0       统计前（-time）个小时的数据 ，这里的单位是小时
     */
    final private long start_time;
    private long start_hour;
    private long current;


    /**
     * 需要统计多少个小时的数据
     */
    private int count;

    public PhoneDataExtractor(final long time) {
        this.current = System.currentTimeMillis();
        this.start_time = time>current?-2:time; //如果不符合规范，默认统计前2小时数据
    }


    private void _calculateHours(final long earliest) {

        Logger.debug(earliest);
        if (start_time>=0){
            this.start_hour = (Math.max(start_time, earliest)/3600000)*3600000;
            this.count = (int) ((current-start_hour)/3600000);
        }else {
            //start_time<0表示的是统计当前截止的前（-start_time）个小时的数据
            this.start_hour = (current/3600000+start_time)*3600000;
            this.count = (int) -start_time;
        }
    }

    public HourStatistic extract(List<PhoneJson> phoneJsonList) {


        if (phoneJsonList==null&&phoneJsonList.size()==0) {
            Logger.println("[empty] "+phoneJsonList);
            return null;
        }

        _calculateHours(phoneJsonList.get(0).getTime());

        if (count<=0) {
            Logger.println("[count] : "+count);
            return null;
        }

        HourStatistic result = new HourStatistic();
        CustomerFlowAnalyze customerFlowElements
                = new CustomerFlowAnalyze(start_hour, count, phoneJsonList);
        customerFlowElements.analyze();

        result.setCustomerFlowElements(customerFlowElements.getCustomerFlowElements());
        result.setCycles(customerFlowElements.getCycles());
        result.setInStoreHours(customerFlowElements.getInStoreHours());
        result.setNewOldCustomElements(customerFlowElements.getNewOldCustomElements());

        return result;
    }



}
