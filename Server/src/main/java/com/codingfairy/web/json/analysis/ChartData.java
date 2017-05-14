package com.codingfairy.web.json.analysis;

import lombok.Data;

import java.util.List;

/**
 * Created by darxan on 2017/5/14.
 * chart data
 */
@Data
public class ChartData<V> {
    /*** sum in yData*/
    protected V sum ;
    /*** mean in yData*/
    protected V mean ;
    /*** maximal value in yData */
    protected V max;
    /*** minimal value in yData*/
    protected V min;
    /**
     * 如果是瞬时的图表，那么xData表示时间点，长度等于yData的长度
     * 如果是时间段统计的图表，那么每两个xData表示一个时间段，长度等于yData.length+(1/0)
     */
    protected List<V> statistic;
}
