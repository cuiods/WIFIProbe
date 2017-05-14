package com.codingfairy.web.json.analysis;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by darxan on 2017/5/14.
 *
 */
@Data
public abstract class ChartData<T extends Serializable,V extends Number> implements Serializable {

    /**wifi探针标识号，用于区别每个探针**/
    private String wifiProb;

    /*** sum in yData*/
    private V sum ;
    /*** mean in yData*/
    private V mean ;
    /*** maximal value in yData */
    private V max;
    /*** minimal value in yData*/
    private V min;
    /**
     * 如果是瞬时的图表，那么xData表示时间点，长度等于yData的长度
     * 如果是时间段统计的图表，那么每两个xData表示一个时间段，长度等于yData.length+(1/0)
     */
    private List<T> xData;
    private List<V> yData;

}
