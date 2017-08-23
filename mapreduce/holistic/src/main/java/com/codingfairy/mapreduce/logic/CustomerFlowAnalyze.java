package com.codingfairy.mapreduce.logic;


import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import lombok.Data;

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

    //统计：客户流量
    private CustomerFlowElement[] customerFlowElements;
    //统计：新老客户
    private NewOldCustomElement[] newOldCustomElements;
    //统计：回访周期
    private List<Long> cycles ;
    //统计：入店时长
    private List<Long> inStoreHours;

    private FlowState flowState;

    public void analyze() {

        customerFlowElements = new CustomerFlowElement[count];
        newOldCustomElements = new NewOldCustomElement[count];
        flowState =  new FlowState(startHour);

        for (int i=0; i<count; i++) {
            analyze(i);
        }

        cycles = flowState.getCycles();
        inStoreHours = flowState.getInStoreHours();

    }

    //该小时范围在phoneJsons数组中的开始搜索位置
    private int searchIndex = 0;

    private void analyze(int hourIndex) {

        Logger.println("[hourIndex]: "+hourIndex);

        //设置flowState的新状态，统计新的一个小时的数据
        flowState.newHourIndex(hourIndex);

        // 移动到第一个有效的数据，
        //
        while (phoneJsons.size()>searchIndex &&
                flowState.accept(phoneJsons.get(searchIndex++)));

        if (searchIndex>0) {
            // overlap一个保证不丢失，主要是应对时间是整点上的数据
            searchIndex--;
        }

        flowState.summary();

        customerFlowElements[hourIndex] = flowState.getCustomerFlowElement();
        newOldCustomElements[hourIndex] = flowState.getNewOldCustom();

    }



}
