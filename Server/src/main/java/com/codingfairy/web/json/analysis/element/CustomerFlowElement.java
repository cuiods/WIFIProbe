package com.codingfairy.web.json.analysis.element;

import lombok.Data;

/**
 * Created by darxan on 2017/5/14.
 *
 * 店铺或区域整体客流及趋势
 * CustomerFlow是对客流量的数据表示。
 *
 * 横坐标表示某个时间点（某整点毫秒）（时间点之间的形成的时间段）
 * 总总表表示某个时间段的客流量
 */
@Data
public class CustomerFlowElement  {

    protected String wifiProb;
    private Long hour;

    private int inNoOutWifi;
    private int inNoOutStore;
    private int outNoInWifi;
    private int outNoInStore;
    private int inAndOutWifi;
    private int inAndOutStore;
    private int stayInWifi;
    private int stayInStore;

    private double jumpRate;//
    private double deepVisit;//深访率：进⼊店铺深度访问的顾客及占⽐(占总体客流)
    private double inStoreRate;//进入店铺或区域的客流占全部客流的比例及趋势

    private int getTotalInWifi() {
        return inNoOutWifi+inAndOutWifi;
    }

    private int getTotalOutWifi() {
        return outNoInWifi+inAndOutWifi;
    }

    private int getTotalInStore() {
        return inNoOutStore+inAndOutStore;
    }

    private int getTotalOutStore() {
        return outNoInStore+inAndOutStore;
    }


}
