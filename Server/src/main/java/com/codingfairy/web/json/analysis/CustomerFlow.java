package com.codingfairy.web.json.analysis;

import com.codingfairy.web.json.analysis.element.CustomerFlowElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by darxan on 2017/5/14.
 *
 * 店铺或区域整体客流及趋势
 * CustomerFlow是对客流量的数据表示。
 *
 * 横坐标表示某个时间点（某整点毫秒）（时间点之间的形成的时间段）
 * 总总表表示某个时间段的客流量
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerFlow extends ProbStatisticData<CustomerFlowElement> {

}
