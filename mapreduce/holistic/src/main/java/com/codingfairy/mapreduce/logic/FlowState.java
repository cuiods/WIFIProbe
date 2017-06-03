package com.codingfairy.mapreduce.logic;

import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import lombok.Data;

import java.util.List;

/**
 * Created by darxan on 2017/6/2.
 */
@Data
public class FlowState {

    //该小时统计的客户流量
    private CustomerFlowElement customerFlowElement;
    //该小时统计的新老客户数据
    private NewOldCustomElement newOldCustom;

    //统计：回访周期
    private List<Long> cycles ;
    //统计：入店时长
    private List<Long> inStoreHours;


    //该小时范围的开始毫秒数
    private long startTime;
    //该小时范围的结束毫秒数
    private long endTime;
    //统计的初始小时，与该小时无关
    private long startHour;

    //是否曾经来过
    private boolean isNew ;
    //上一次入店的时间
    private long lastInStoreStartTime;
    //上一次在店内的时刻
    private long lastInStore ;
    //上一次在指针范围内的时刻
    private long lastInWifi ;
    //上一次在指针范围内的时间
    private long lastInWifiStartTime;


    protected long countOfInWifi;
    protected long countOfInStore;

    protected int inAndOutStore;
    protected int lastInNoOutStore;
    protected int firstOutNoInStore;

    protected int inAndOutWifi;
    protected int lastInNoOutWifi;
    protected int firstOutNoInWifi;


    public FlowState(long startHour){
        this.startHour = startHour;
        this.isNew = true;
        this.lastInStoreStartTime = -1;
        this.lastInWifiStartTime = -1;
        this.lastInStore = -1;
        this.lastInWifi = -1;

        this.inAndOutStore = 0;
        this.lastInNoOutStore = 0;
        this.firstOutNoInStore = 0;

        this.inAndOutWifi = 0;
        this.lastInNoOutWifi = 0;
        this.firstOutNoInWifi = 0;

    }

    public void newHourIndex(int hourIndex) {
        startTime = startHour + (long) hourIndex*(long) 3600000;
        endTime = startHour + (long) (hourIndex+1)*(long) 3600000;

        customerFlowElement = new CustomerFlowElement();
        newOldCustom = new NewOldCustomElement();
    }

    /**
     * 如果time已经越界 ，返回false
     * @param data
     * @return
     */
    public boolean accept(PhoneJson data) {

        if (data.getTime()>endTime) {
            return false;
        }

        //in store
        if (InStoreJudge.isInStore(data)) {
            inStoreAction( data);
        }
        //in wifi
        inWifiAction(data);

        return true;
    }


    protected void inStoreAction(PhoneJson data) {
        if (data.getTime()<endTime && data.getTime()>startTime) {
            countOfInStore++;
        }
        if (lastInStore >0) {
            boolean isBreak = !IsContinuous.isContinuous(lastInStore, data);
            if (isBreak) {
                if (lastInStoreStartTime<startTime && data.getTime()<data.getTime()) {
                    //abandoned
                } else if (lastInStoreStartTime<startTime && data.getTime()>data.getTime()) {
                    firstOutNoInStore = 1;
                } else if (lastInStoreStartTime>startTime && data.getTime()>data.getTime()) {
                    inAndOutStore++;
                }
                cycles.add(data.getTime()-lastInStoreStartTime);
                inStoreHours.add(lastInStore-lastInStoreStartTime);
                lastInStoreStartTime = data.getTime();
            }
        } else {
            lastInStoreStartTime = data.getTime();
        }
        lastInStore = data.getTime();
        isNew = false;
    }

    protected void inWifiAction(PhoneJson data) {

        if (data.getTime()<endTime && data.getTime()>startTime) {
            countOfInWifi++;
        }

        if (lastInWifi>0) {

            boolean isBreak = !IsContinuous.isContinuous(lastInWifi, data);

            if (isBreak) {
                if (lastInWifiStartTime<startTime && data.getTime()<startTime) {
                    //abandoned
                } else if (lastInWifiStartTime<startTime && data.getTime()>startTime) {
                    firstOutNoInWifi = 1;
                } else if (lastInWifiStartTime<startTime && data.getTime()<startTime) {
                    inAndOutWifi++;
                }

                lastInWifiStartTime = data.getTime();
            }
        } else {
            lastInWifiStartTime = data.getTime();
        }
    }



    public void summary() {

        if (inAndOutStore==0 && firstOutNoInStore==0) {
            if (countOfInStore>0) {
                customerFlowElement.setStayInStore(1);
            }
        } else {
            if (IsContinuous.isContinuous(lastInStore, endTime)) {
                lastInNoOutStore = 1;
            } else {
                inAndOutStore ++;
            }
            customerFlowElement.setInAndOutStore(inAndOutStore);
            customerFlowElement.setInNoOutStore(lastInNoOutStore);
            customerFlowElement.setOutNoInStore(firstOutNoInStore);
        }

        if (inAndOutWifi==0 && firstOutNoInWifi==0) {
            if (countOfInWifi>0) {
                customerFlowElement.setStayInWifi(1);
            }
        } else {
            if (IsContinuous.isContinuous(lastInWifi, endTime)) {
                lastInNoOutWifi = 1;
            } else {
                inAndOutWifi++;
            }
            customerFlowElement.setInAndOutWifi(inAndOutWifi);
            customerFlowElement.setInNoOutWifi(lastInNoOutWifi);
            customerFlowElement.setOutNoInWifi(firstOutNoInWifi);
        }

    }

 }
