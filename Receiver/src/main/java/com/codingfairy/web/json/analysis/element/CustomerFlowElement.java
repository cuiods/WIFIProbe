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

    /**
     * 比如把一个小时用分：秒表示
     *
     * 一个用户一个小时的的统计序列是（一个时间点表示用户该时间在商店内）：
     *
     *  0：00 0：01 0:02   10:00 10:01 10:02  20；01 20：02      59：58 59:59 60；00
     *  ^^^^^^^^^^^^^^^    ^^^^^^^^^^^^^^^^   ^^^^^^^^^^^^      ^^^^^^^^^^^^^^^^^^^
     *  ^             ^    进出一次：          进出一次           进入一次且最终没有出去
     *  ^             ^    inAndOutWifi++；   inAndOutWifi++；   inNoOutWifi++
     *  ^             ^
     *  之前就进入了   ^
     *                out:outNoIn=1
     *
     * 有：
     * inAndOutWifi:2
     * inNoOut:0
     * outNoIn:1
     *
     * outNoInStore： 是统计这一个小时如果开始之前就进入了这个商店，且在这个小时内出去了
     * inAndOutStore: 是统计的这一个小时内前后用户进出商店的次数，每个用户可能有多次
     * inNoOutStore: 是统计的这一个小时内的最后时刻还是呆在里面的用户数
     * stayInStore： 是统计这一个小时如果开始之前就进入了这个商店，且在这个小时内没有出去。
     *               是指整个小时一直呆在商店里面没有出去过
     *
     */
    //是统计的这一个小时内的最后时刻还是呆在wifi区域
    private int inNoOutWifi;
    //是统计的这一个小时内的最后时刻还是呆在商店里面
    private int inNoOutStore;

    // 是统计这一个小时如果开始之前就进入了这个wifi区域，且在这个小时内出去了
    private int outNoInWifi;
    // 是统计这一个小时如果开始之前就进入了这个商店，且在这个小时内出去了
    private int outNoInStore;

    //是统计的这一个小时内前后用户进出wifi区域的次数
    private int inAndOutWifi;
    //是统计的这一个小时内前后用户进出商店的次数
    private int inAndOutStore;

    /**
     * 是统计这一个小时如果开始之前就进入了WIFI区域，且在这个小时内没有出去。
     *               是指整个小时一直呆在商店里面没有出去过
     */
    private int stayInWifi;
    /**
     * 是统计这一个小时如果开始之前就进入了这个商店，且在这个小时内没有出去。
     *               是指整个小时一直呆在商店里面没有出去过
     */
    private int stayInStore;

    /**
     * 访问商店很短时间就离开的用户所占的比率
     */
    private double jumpRate;//
    /**
     * 访问商店很长时间菜离开的用户所占的比率
     */
    private double deepVisit;//深访率：进⼊店铺深度访问的顾客及占⽐(占总体客流)
    /**
     * 进入WIFI区域的用户中进入了商店的用户占比
     */
    private double inStoreRate;//进入店铺或区域的客流占全部客流的比例及趋势

}
