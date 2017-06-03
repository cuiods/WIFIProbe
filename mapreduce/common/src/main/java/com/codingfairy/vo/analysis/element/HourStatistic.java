package com.codingfairy.vo.analysis.element;

import com.codingfairy.vo.PhoneJson;
import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

/**
 * Created by darxan on 17-5-17.
 */
@Data
public class HourStatistic {



    public HourStatistic() {
    }

    private CustomerFlowElement[] customerFlowElements;
    private NewOldCustomElement[] newOldCustomElements;
    private List<Long> cycles ;
    private List<Long> inStoreHours;


}
