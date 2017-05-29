package com.codingfairy.vo.analysis.element;

import com.codingfairy.vo.Tuple;
import com.codingfairy.vo.analysis.ChartData;
import lombok.Data;

/**
 * Created by darxan on 2017/5/14.
 *
 * 驻店时长：进⼊店铺的顾客在店内的停留时长
 * 横坐标表示顾客在店内的停留时长（毫秒计算）
 * 纵坐标表示对应停留时长的人数
 *
 * 统计该小时内离去的客户的驻店时长
 */
@Data
public class InStoreHoursElement extends ChartData<Tuple<Long, Integer>>{
    protected String wifiProb;
    private Long hour;
}
