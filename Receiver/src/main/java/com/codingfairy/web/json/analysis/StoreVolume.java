package com.codingfairy.web.json.analysis;

import lombok.Data;

/**
 * Created by darxan on 2017/5/14.
 * 进入店铺或区域的客流及趋势
 *
 * 横坐标表示某个时间点（某整点毫秒）（时间点之间的形成的时间段）
 * 总总表表示某个时间段的客流量
 */
@Data
public class StoreVolume extends ChartData<Long, Long>{

}
