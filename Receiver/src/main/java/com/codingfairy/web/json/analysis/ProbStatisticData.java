package com.codingfairy.web.json.analysis;

import lombok.Data;

import java.io.Serializable;
/**
 * Created by darxan on 2017/5/14.
 *
 */
@Data
public abstract class ProbStatisticData<V>
        extends ChartData<V>
        implements Serializable {

    /**wifi探针标识号，用于区别每个探针**/
    protected String wifiProb;

}
