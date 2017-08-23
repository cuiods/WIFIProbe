package com.codingfairy.mapreduce.logic;

import com.codingfairy.mapreduce.config.InStoreJudge;
import com.codingfairy.mapreduce.config.IsContinuous;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import lombok.Data;

import java.util.ArrayList;
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
    //上一次在店内的时刻
    private long lastInStore ;
    //上一次入店的时间
    private long lastInStoreStartTime;

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


    private int newCustomer;
    private int oldCustomer;

    public FlowState(long startHour) {

        this.startHour = startHour;

        this.cycles = new ArrayList<Long>();
        this.inStoreHours = new ArrayList<Long>();

        this.isNew = true;

        this.lastInStoreStartTime = -1;
        this.lastInWifiStartTime = -1;
        this.lastInStore = -1;
        this.lastInWifi = -1;

        _initForHour();
    }

    private void _initForHour() {

        this.inAndOutStore = 0;
        this.lastInNoOutStore = 0;
        this.firstOutNoInStore = 0;

        this.inAndOutWifi = 0;
        this.lastInNoOutWifi = 0;
        this.firstOutNoInWifi = 0;

        this.newCustomer = 0;
        this.oldCustomer = 0;
        this.countOfInStore = 0;
        this.countOfInWifi = 0;

    }

    public void newHourIndex(int hourIndex) {

        Logger.println("[ new hour ] "+hourIndex);

        startTime = startHour + (long) hourIndex*(long) 3600000;
        endTime = startHour + (long) (hourIndex+1)*(long) 3600000;

        customerFlowElement = new CustomerFlowElement();
        newOldCustom = new NewOldCustomElement();

        customerFlowElement.setHour(startTime);
        newOldCustom.setHour(endTime);

        _initForHour();
    }

    /**
     * 如果time已经越界 ，返回false
     * @param data
     * @return
     */
    public boolean accept(PhoneJson data) {

        Logger.println("[data time] "+data.getTime());

        if (data.getTime()>endTime) {
            Logger.println("[out hour]");
            return false;
        }

        //in store
        if (InStoreJudge.isInStore(data)) {
            Logger.println("[in sore]");
            inStoreAction( data);
        }
        //in wifi
        Logger.println("[in wifi]");
        inWifiAction(data);

        return true;
    }


    protected void inStoreAction(PhoneJson data) {

        if (data.getTime()<endTime && data.getTime()>startTime) {

            Logger.println("[in range]");
            countOfInStore++;
        }

        if (lastInStore >0) {

            boolean isBreak = !IsContinuous.isContinuous(lastInStore, data);
            Logger.println("[isBreak] "+isBreak);

            if (isBreak) {

                if (lastInStoreStartTime<startTime && endTime<data.getTime()) {
                    //abandoned
                } else if (lastInStoreStartTime<startTime && endTime>data.getTime()) {
                    firstOutNoInStore = 1;
                } else if (lastInStoreStartTime>=startTime && endTime>data.getTime()) {
                    inAndOutStore++;
                }

                cycles.add(data.getTime()-lastInStoreStartTime);
                inStoreHours.add(lastInStore-lastInStoreStartTime);
                lastInStoreStartTime = data.getTime();

                if (data.getTime()<endTime && data.getTime()>startTime) {
                    oldCustomer++;
                }

            }
        } else {

            lastInStoreStartTime = data.getTime();

            if (data.getTime()<endTime && data.getTime()>startTime) {
                newCustomer = 1;
            }

            isNew = false;
        }

        lastInStore = data.getTime();

    }

    protected void inWifiAction(PhoneJson data) {

        if (data.getTime()<endTime && data.getTime()>startTime) {
            countOfInWifi++;
        }

        if (lastInWifi>0) {

            boolean isBreak = !IsContinuous.isContinuous(lastInWifi, data);

            if (isBreak) {

                if (lastInWifiStartTime<startTime && endTime<data.getTime()) {
                    //abandoned
                } else if (lastInWifiStartTime<startTime && endTime>data.getTime()) {
                    firstOutNoInWifi = 1;
                } else if (lastInWifiStartTime>=startTime && endTime>data.getTime()) {
                    inAndOutWifi++;
                }

                lastInWifiStartTime = data.getTime();
            }

        }
        else {
            // 这是当前小时内对应的第一个数据
            lastInWifiStartTime = data.getTime();
        }

        lastInWifi = data.getTime();
    }


    public void summary() {

        newOldCustom.setNewCustomer(newCustomer);
        newOldCustom.setOldCustomer(oldCustomer);

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
