package com.codingfairy.mapreduce.logic.analyze;


import com.codingfairy.mapreduce.logic.InStoreJudge;
import com.codingfairy.mapreduce.logic.IsContinuous;
import com.codingfairy.mapreduce.logic.IsDeepVisitor;
import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 17-5-17.
 */
@Data
public class CustomerFlowAnalyze {
    
    private long startHour;
    private int count;
    private List<PhoneJson> phoneJsons;
    
    public CustomerFlowAnalyze(long startHour, int count, List<PhoneJson> phoneJsons) {
        this.startHour = startHour;
        this.count = count;
        this.phoneJsons = phoneJsons;
    }

    private CustomerFlowElement[] customerFlowElements;
    private NewOldCustomElement[] newOldCustomElements;
    private List<Long> cycles ;
    private List<Long> inStoreHours;


    public void analyze() {

        customerFlowElements = new CustomerFlowElement[count];
        newOldCustomElements = new NewOldCustomElement[count];
        cycles = new ArrayList<Long>();
        inStoreHours = new ArrayList<Long>();


        for (int i=0; i<count; i++) {
            analyze(i);
        }

    }

    private long startTime;
    private long endTime;
    private int searchIndex = 0;

    private CustomerFlowElement customerFlowElement;
    private NewOldCustomElement newOldCustom;


    private void analyze(int hourIndex) {

        startTime = startHour + (long) hourIndex*(long) 3600000;
        endTime = startHour + (long) (hourIndex+1)*(long) 3600000;

        customerFlowElement = new CustomerFlowElement();
        newOldCustom = new NewOldCustomElement();

        customerFlowElements[hourIndex] = customerFlowElement;
        newOldCustomElements[hourIndex] = newOldCustom;

        //move to the first valid position
        for (;;searchIndex++) {
            if (searchIndex>=phoneJsons.size()){
                Logger.println("all time is too earlier");
                return ;
            }
            if (phoneJsons.get(searchIndex).getTime()>=startTime) {
               break;
            }

            Logger.println("--- time is too earlier");
        }
        analyzeInStore(false);
        analyzeInWifi(true);


        customerFlowElement.setDeepVisit(deepVisitCount);
        customerFlowElement.setInStoreRate(customerFlowElement.getTotalInStore()>0?1:0);
        customerFlowElement.setTotal(1);
        customerFlowElement.setJumpRate(jumpCount);
        customerFlowElement.setHour(startTime);

    }

    private boolean isNew = true;
    private long lastStartInTime = -1;

    private int deepVisitCount = -1;
    private int jumpCount = -1;

    private void analyzeInStore(boolean setIndex) {

        deepVisitCount = 0;
        jumpCount = 0;

        if (searchIndex >= phoneJsons.size()) {
            return;
        }

        int i;
        int out = 0;
        int in = 0;

        PhoneJson last = phoneJsons.get(searchIndex);
        boolean firstInWifi = IsContinuous.isContinuous(startTime, last) && InStoreJudge.isInStore(last);
        boolean beforeLoopIsNew = isNew;

        if (firstInWifi) {
            isNew = false;
        }

        boolean endInfWifi = false;
        boolean lastInWifi = firstInWifi;


        for ( i=searchIndex+1; true; i++) {

            if (i>=phoneJsons.size()) {
                endInfWifi = (IsContinuous.isContinuous(endTime, last))&&InStoreJudge.isInStore(last);
                if (!endInfWifi && lastInWifi) {
                    out++;
                    inStoreHours.add(last.getTime()-lastStartInTime);
                    Logger.debug("reach to list end, out at: "+last.getTime());
                    Logger.debug("in store time is : "+(last.getTime()-lastStartInTime));
                }
                Logger.debug("end at: "+last.getTime());
                break;
            }

            PhoneJson current = phoneJsons.get(i);

            boolean isContinuous = IsContinuous.isContinuous(last, current);
            boolean isIn = InStoreJudge.isInStore(current);

            if (!isIn) {
                Logger.debug("skip one at: "+current.getTime());
                continue;
            }

            //last in wifi == true && isIn==true
            if (lastInWifi && (!isContinuous||!isIn)) {
                out++;
                inStoreHours.add(last.getTime()-lastStartInTime);
                if (IsDeepVisitor.isDeepVist(last.getTime()-lastStartInTime)) {
                    deepVisitCount++;
                }else {
                    jumpCount++;
                }
                Logger.debug("out at: "+current.getTime());
                Logger.debug("in store time is : "+(last.getTime()-lastStartInTime));
            }

            if ((!lastInWifi||!isContinuous) && isIn) {
                if (lastStartInTime>0) {
                    cycles.add(current.getTime()-lastStartInTime);
                }

                lastStartInTime = current.getTime();
                in++;
                isNew = false;
                Logger.debug("in at: "+current.getTime());

            }



            if (current.getTime() >= endTime) {
                if (isContinuous && isIn) {
                    endInfWifi = true;
                    Logger.debug("out at: "+current.getTime());
                }
                Logger.debug("out time  at: "+last.getTime());
                break;

            }

            last = current;
            lastInWifi = isIn;
        }


        int stayInWifi = firstInWifi&&out==0?1:0;
        int inNoOutWifi = endInfWifi&&(out>1||firstInWifi) ? 1 : 0;
        int outNoInWifi = out>0&&firstInWifi ? 1 : 0;
        int inAndOutWifi = Math.min(in-inNoOutWifi, out-outNoInWifi);

        Logger.println("in :"+in);
        Logger.println("out:"+out);
        Logger.println("firstInWifi:"+firstInWifi);
        Logger.println("endInfWifi:"+endInfWifi);

        customerFlowElement.setInAndOutStore(inAndOutWifi);
        customerFlowElement.setInNoOutStore(inNoOutWifi);
        customerFlowElement.setOutNoInStore(outNoInWifi);
        customerFlowElement.setStayInStore(stayInWifi);

        newOldCustom.setNewCustomer(beforeLoopIsNew&&in>0?1:0);
        newOldCustom.setOldCustomer(in-newOldCustom.getNewCustomer());

        if (setIndex) {
            searchIndex = i;
        }
    }

    private void analyzeInWifi(boolean setIndex) {
        if (searchIndex>=phoneJsons.size()) {
            return;
        }


        int i;

        int out = 0;
        int in = 0;

        PhoneJson last = phoneJsons.get(searchIndex);

        boolean firstInWifi = IsContinuous.isContinuous(startTime, last);
        boolean endInfWifi = false;


        for ( i=searchIndex+1; true; i++) {

            if (i>=phoneJsons.size()) {
                endInfWifi = (IsContinuous.isContinuous(endTime, last));
                if (!endInfWifi) {
                    out++;
                }
                break;
            }

            PhoneJson current = phoneJsons.get(i);
            boolean isContinuous = IsContinuous.isContinuous(last, current);

            if (!isContinuous) {
                in++;
                out++;
            }

            if (isContinuous) {
                //do no thing
            }

            if (current.getTime() >= endTime) {
                if (isContinuous) {
                    endInfWifi = true;
                }
                break;
            }

            last = current;
        }

        int stayInWifi = firstInWifi&&out==0?1:0;
        int inNoOutWifi = endInfWifi&&(out>1||firstInWifi) ? 1 : 0;
        int outNoInWifi = out>0&&firstInWifi ? 1 : 0;
        int inAndOutWifi = Math.min(in-inNoOutWifi, out-outNoInWifi);

        customerFlowElement.setInAndOutWifi(inAndOutWifi);
        customerFlowElement.setInNoOutWifi(inNoOutWifi);
        customerFlowElement.setOutNoInWifi(outNoInWifi);
        customerFlowElement.setStayInWifi(stayInWifi);


        if (setIndex) {
            searchIndex = i;
        }
    }
}
